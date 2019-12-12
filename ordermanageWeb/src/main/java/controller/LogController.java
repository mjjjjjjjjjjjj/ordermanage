package controller;

import domain.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import service.LogService;

import java.util.List;

@Controller
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;

    @RequestMapping("/allL")
    public String findAllLog(ModelMap map){
        List<Log> allLog = logService.findAllLog();
        map.addAttribute("allLog",allLog);
        return "/log-list.jsp";
    }
}
