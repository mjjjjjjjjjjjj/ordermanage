package controller;

import com.github.pagehelper.PageInfo;
import domain.Member;
import domain.Orders;
import domain.Product;
import domain.Traveller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.MemberService;
import service.OrdersService;
import service.ProductService;
import service.TravellerService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ProductService productService;
    @Autowired
    private TravellerService travellerService;

    @RequestMapping("/allO")
    public String findAllOrders(HttpServletRequest req, ModelMap map, @RequestParam(defaultValue = "1")int pageNum,@RequestParam(defaultValue = "5") int pageSize){
        ordersService.checkIfPay();
        PageInfo<Orders> allOrders = ordersService.findAllOrders(pageNum, pageSize);
        map.addAttribute("orders",allOrders);
        return "/orders-list.jsp";
    }

    @RequestMapping("/idO")
    public String findOrderById(HttpServletRequest req,ModelMap map,String id){
        Orders ordersById = ordersService.findOrdersById(id);
        map.addAttribute("orders",ordersById);
        return "/orders-show.jsp";
    }

    @RequestMapping("/prepare")
    public String addOrdersPrepare(HttpServletRequest req,ModelMap map){
        List<Member> allM = memberService.findAllM();
        List<Product> allP = productService.findAllProduct2();
        List<Traveller> allT = travellerService.findAllT();
        map.addAttribute("allM",allM);
        map.addAttribute("allP",allP);
        map.addAttribute("allT",allT);
        return "/orders-add.jsp";
    }

    @RequestMapping("/addO")
    public String addOrders(HttpServletRequest req,ModelMap map,String orderNum,String productNum,
                            String memberId,String[] travellerId,
                            @RequestParam(defaultValue = "1")int pageNum,
                            @RequestParam(defaultValue = "5") int pageSize){
        Member m = memberService.findMemberById(memberId);
        Product p = productService.findByProductNum(productNum);
        List<Traveller> listT = new ArrayList<>();
        for (String id:travellerId){
            listT.add(travellerService.findByTravellerId(id));
        }
        Orders orders = ordersService.packageOrder(orderNum, m, p, listT);
        ordersService.addOrders(orders,listT);

        ordersService.checkIfPay();
        PageInfo<Orders> allOrders = ordersService.findAllOrders(pageNum, pageSize);
        map.addAttribute("orders",allOrders);
        return "/orders-list.jsp";
    }

    @RequestMapping("/payO")
    public String payOrders(HttpServletRequest req,ModelMap map,String id,
                            @RequestParam(defaultValue = "1")int pageNum,
                            @RequestParam(defaultValue = "5") int pageSize){
        ordersService.payOrders(id);
        ordersService.checkIfPay();
        PageInfo<Orders> allOrders = ordersService.findAllOrders(pageNum, pageSize);
        map.addAttribute("orders",allOrders);
        return "/orders-list.jsp";
    }
}
