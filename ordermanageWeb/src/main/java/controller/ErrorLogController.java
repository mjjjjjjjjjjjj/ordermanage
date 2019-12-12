package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.ErrorLogService;

@Controller
@RequestMapping("/errorlog")
public class ErrorLogController {
    @Autowired
    private ErrorLogService errorLogService;


}
