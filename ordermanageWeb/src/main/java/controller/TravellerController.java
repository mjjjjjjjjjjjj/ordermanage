package controller;

import domain.Traveller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.TravellerService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/traveller")
public class TravellerController {
    @Autowired
    private TravellerService travellerService;

    @RequestMapping("/findT")
    public @ResponseBody List<Traveller> findTravellerById(HttpServletRequest req, String id){
        List<Traveller> travellerBymid = travellerService.findTravellerBymid(id);
        return travellerBymid;
    }
}
