package service;

import dao.LogDao;
import domain.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    @Autowired
    private LogDao logDao;

    public void addLog(Log log){
        logDao.addLogs(log);
    }

    public List<Log> findAllLog(){
        return logDao.findAllLog();
    }
}
