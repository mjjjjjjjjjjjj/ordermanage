package controller;

import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import service.CustomerService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/allC")
    @Secured({"ROLE_SUPER"})
    public String findAllCustomer(HttpServletRequest req, ModelMap map){
        List<Customer> allC = customerService.findAllC();
        map.addAttribute("allC",allC);
        return "/customer-list.jsp";
    }

    @RequestMapping("/addC")
    public String addCustomer(HttpServletRequest req,Customer customer){
        customerService.addCustomer(customer);
        return "redirect:"+req.getContextPath()+"/customer/allC";
    }

    @RequestMapping("/detail")
    public String customerDetail(HttpServletRequest req, ModelMap map,String id){
        Customer customer = customerService.findCustomerById(id);
        map.addAttribute("customer",customer);
        return "/customer-show.jsp";
    }

    @RequestMapping("/addRtC")
    public String addRoleToCustomer(HttpServletRequest req, String userId,String[] ids){
        customerService.addRoleToCustomer(userId,ids);
        return "redirect:"+req.getContextPath()+"/customer/allC";
    }
}
