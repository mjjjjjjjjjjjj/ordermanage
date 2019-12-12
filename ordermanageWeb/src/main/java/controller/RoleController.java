package controller;

import domain.Permission;
import domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import service.RoleService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/allR")
    public String findAllRole(HttpServletRequest req, ModelMap map){
        List<Role> allR = roleService.findAllR();
        map.addAttribute("allR",allR);
        return "/role-list.jsp";
    }

    @RequestMapping("/addR")
    public String addRole(HttpServletRequest req,Role role){
        roleService.addRole(role);
        return "redirect:"+req.getContextPath()+"/role/allR";
    }

    @RequestMapping("/notR")
    public String findNotRole(HttpServletRequest req,ModelMap map,String id){
        List<Role> notRole = roleService.findNotRole(id);
        map.addAttribute("cid",id);
        map.addAttribute("notRole",notRole);
        return "/customer_role_add.jsp";
    }

    @RequestMapping("/findById")
    public String findRoleById(HttpServletRequest req,ModelMap map,String id){
        Role roleById = roleService.findRoleById2(id);
        map.addAttribute("roles",roleById);
        return "/role-show.jsp";
    }

    @RequestMapping("/transferR")
    public String transferRole(HttpServletRequest req,ModelMap map,String id){
        map.addAttribute("id",id);
        return "/role-change.jsp";
    }

    @RequestMapping("/changeR")
    public String changeRole(HttpServletRequest req,Role role){
        roleService.changeRole(role);
        return "redirect:"+req.getContextPath()+"/role/allR";
    }

    @RequestMapping("/addPtoR")
    public String addPerToRole(HttpServletRequest req, String userId, String[] ids){
        roleService.addPtoR(userId,ids);
        return "redirect:"+req.getContextPath()+"/role/allR";
    }
}
