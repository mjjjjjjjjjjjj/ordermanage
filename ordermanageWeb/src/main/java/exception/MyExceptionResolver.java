package exception;


import dao.ErrorLogDao;
import domain.ErrorLog;
import domain.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import util.MailUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

public class MyExceptionResolver implements HandlerExceptionResolver {
    @Autowired
    private ErrorLogDao errorLogDao;

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        String email = "1109712984@qq.com";
        String code = "6666";

        ErrorLog log = errorLogDao.findNewErrorLog();
        errorLogDao.addCode(code,log.getId());

        if(!(e instanceof MyException)) {
            e = new MyException("系统异常",code);
        }
        // 获取异常栈信息
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        e.printStackTrace(new java.io.PrintWriter(buf, true));
        String expMessage = buf.toString();

        //email
        String errorText = "错误码："+((MyException) e).getCode()+
                "<br>异常信息:"+e.getMessage()+
                "<br>异常栈信息:"+expMessage;
        MailUtils mailutils = new MailUtils(email, errorText, "系统异常");
        Thread thread = new Thread(mailutils);
        thread.start();
        ModelMap modelMap = modelAndView.getModelMap();
        modelMap.addAttribute("code",code);
        modelAndView.setViewName("/error2.jsp");
        return modelAndView;
    }
}
