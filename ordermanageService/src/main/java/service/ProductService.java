package service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dao.ProductDao;
import domain.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    public PageInfo<Product> findAllProduct(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Product> products = productDao.allProduct();
        return new PageInfo<>(products);
    }

    public void addProduct(Product product){
        product.setId(UUID.randomUUID().toString());
        productDao.addProduct(product);
    }

    public void delProduct(String[] productNum){
        for (String id : productNum){
            productDao.delProduct(id);
        }
    }

    public Product findByProductNum(String productNum){
        return productDao.findByProductNum(productNum);
    }

    public void updateProduct(Product product){
        productDao.updateProduct(product);
    }

    public void changeStatus(String[] productNum,int statu){
        for(String id:productNum){
            Map map = new HashMap();
            map.put("productNum",id);
            map.put("productStatus",statu);
            productDao.changeStatus(map);
        }
    }

    public List<Product> findByKeyword(String keyword){
        String keyword2 = "%"+keyword+"%";
        List<Product> byKeyord = productDao.findByKeyord(keyword2);
        return byKeyord;
    }

    public PageInfo<Product> sortAllProduct(int num,int pageNum,int pageSize){
       String orderBy = null;
        if(num == 1){
            orderBy = "productNum ASC";
        }else if(num == 2){
            orderBy = "productPrice ASC";
        }
//        else {
//            orderBy = "depar ASC";
//        }
        PageHelper.startPage(pageNum,pageSize,orderBy);
        List<Product> products = productDao.allProduct();
        return new PageInfo<>(products);
    }

    public List<Product> findAllProduct2(){
        return productDao.allProduct();
    }
}
