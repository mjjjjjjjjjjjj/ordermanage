package controller;

import com.github.pagehelper.PageInfo;
import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.ProductService;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/allP")
    public String allProducts(HttpServletRequest req, ModelMap map,@RequestParam(defaultValue = "1")int pageNum,@RequestParam(defaultValue = "3")int pageSize){
        PageInfo<Product> productPageInfo = productService.findAllProduct(pageNum,pageSize);
        map.addAttribute("products",productPageInfo);
        return "/products-manage.jsp";
    }

    @RequestMapping("/addP")
    public String addProduct(HttpServletRequest req,Product product){
        productService.addProduct(product);
        return "redirect:"+req.getContextPath()+"/product/allP";
    }

    @RequestMapping("/delP")
    public String delProduct(HttpServletRequest req,String[] ids){
        productService.delProduct(ids);
        return "redirect:"+req.getContextPath()+"/product/allP";
    }

    @RequestMapping("/edit")
    public String putAtPage(HttpServletRequest req,ModelMap map,String productNum){
        Product p = productService.findByProductNum(productNum);
        map.addAttribute("product",p);
        return "/products-edit.jsp";
    }

    @RequestMapping("/update")
    public String updateProduct(HttpServletRequest req,Product product){
        productService.updateProduct(product);
        return "redirect:"+req.getContextPath()+"/product/allP";
    }

    @RequestMapping("/openS")
    public String openStatus(HttpServletRequest req,String[] ids){
        productService.changeStatus(ids,1);
        return "redirect:"+req.getContextPath()+"/product/allP";
    }

    @RequestMapping("/closeS")
    public String closeStatus(HttpServletRequest req,String[] ids){
        productService.changeStatus(ids,0);
        return "redirect:"+req.getContextPath()+"/product/allP";
    }

    @RequestMapping("/search")
    public String findKeyWord(HttpServletRequest req,String keyword,ModelMap map){
        List<Product> byKeyword = productService.findByKeyword(keyword);
        map.addAttribute("products",new PageInfo<>(byKeyword));
        return "/products-manage.jsp";
    }

    @RequestMapping("/sortAllP")
    public String sortAllProduct(HttpServletRequest req,int sort,ModelMap map,@RequestParam(defaultValue = "1")int pageNum,@RequestParam(defaultValue = "3")int pageSize){
        PageInfo<Product> pro = productService.sortAllProduct(sort, pageNum, pageSize);
        map.addAttribute("products",pro);
        return "/products-manage.jsp";
    }
}
