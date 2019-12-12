package controller;

import domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import service.PermissionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/allPer")
    public String findAllPermission(HttpServletRequest req, ModelMap map){
        List<Permission> allPermission = permissionService.findAllPermission();
        map.addAttribute("allPermission",allPermission);
        return "/permission-list.jsp";
    }

    @RequestMapping("/addPer")
    public String addPermission(HttpServletRequest req,Permission permission){
        permissionService.addPermission(permission);
        return "redirect:"+req.getContextPath()+"/permission/addPer";
    }

    @RequestMapping("/transferP")
    public String transferPer(HttpServletRequest req,ModelMap map,String id){
        map.addAttribute("id",id);
        return "/permission-change.jsp";
    }

    @RequestMapping("/changeP")
    public String changePermission(HttpServletRequest req, Permission permission){
        permissionService.changePermission(permission);
        return "redirect:"+req.getContextPath()+"/permission/allPer";
    }

    @RequestMapping("/delP")
    public String deletePermission(HttpServletRequest req,String id){
        permissionService.delPermission(id);
        return "redirect:"+req.getContextPath()+"/permission/allPer";
    }

    @RequestMapping("/per")
    @PreAuthorize("hasAnyRole('ROLE_SUPER')")
    public String findPermission(HttpServletRequest req,String rid,ModelMap map){
        List<Permission> permissions = permissionService.findPermission(rid);
        map.addAttribute("permissions",permissions);
        map.addAttribute("rid",rid);
        return "/role_permission_add.jsp";
    }

}
