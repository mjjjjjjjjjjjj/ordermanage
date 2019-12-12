package controller;

import domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import service.MemberService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @RequestMapping("/allM")
    public String findAllMember(HttpServletRequest req,ModelMap map){
        int i = 1/0;
        List<Member> allM = memberService.findAllM();
        map.addAttribute("allM",allM);
        return "/member-list.jsp";
    }

    @RequestMapping("/memDetail")
    public String memberDetail(HttpServletRequest req,ModelMap map,String id){
        Member member = memberService.findMember(id);
        map.addAttribute("member",member);
        return "/member-show.jsp";
    }

}
