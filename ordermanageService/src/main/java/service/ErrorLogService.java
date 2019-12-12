package service;

import dao.ErrorLogDao;
import domain.ErrorLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrorLogService {
    @Autowired
    private ErrorLogDao errorLogDao;

    public void addErrorLog(ErrorLog log){
        errorLogDao.addErrorLog(log);
    }

    public List<ErrorLog> findAllErrorLog(){
        return errorLogDao.allErrorLogs();
    }

    public ErrorLog findNewErrorLog(){
        return errorLogDao.findNewErrorLog();
    }

    public void addCode(String code,String id){
        errorLogDao.addCode(code,id);
    }
}
