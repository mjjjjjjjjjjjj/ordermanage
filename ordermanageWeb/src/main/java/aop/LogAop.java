package aop;

import domain.ErrorLog;
import domain.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import service.ErrorLogService;
import service.LogService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Component
@Aspect
public class LogAop {
    @Autowired
    private LogService logService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ErrorLogService errorLogService;

    private Date visitTime;


    @Pointcut("execution(* controller.*.*(..))")
    public void cut(){}

    @Before("cut()")
    public void beforeC(){
        visitTime = new Date();
    }

    @AfterReturning("cut()")
    public void afterC(JoinPoint joinPoint){
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        String ip = request.getRemoteAddr();
        String uri = request.getRequestURI();

        String method = joinPoint.getSignature().getName();

        Long time = new Date().getTime() - visitTime.getTime();

        Log log = new Log();
        log.setId(UUID.randomUUID().toString());
        log.setVisitTime(visitTime);
        log.setUsername(username);
        log.setExecutionTime(time);
        log.setIp(ip);
        log.setMethod(method);
        log.setUrl(uri);
        logService.addLog(log);
    }

    @AfterThrowing("cut()")
    public void throwException(JoinPoint joinPoint){
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        String ip = request.getRemoteAddr();
        String URI = request.getRequestURI();

        String method = joinPoint.getSignature().getName();

        ErrorLog errorLog = new ErrorLog();
        errorLog.setId(UUID.randomUUID().toString());
        errorLog.setUsername(username);
        errorLog.setVisitTime(visitTime);
        errorLog.setIp(ip);
        errorLog.setUrl(URI);
        errorLog.setMethod(method);
        errorLogService.addErrorLog(errorLog);
    }
}
