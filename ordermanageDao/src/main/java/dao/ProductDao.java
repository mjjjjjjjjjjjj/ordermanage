package dao;

import domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductDao {

    @Select("select * from product where id=#{productId}")
    Product findByProductId(String productId);

    @Select("select * from product")
    List<Product> allProduct();

    @Insert("insert into product values(#{id},#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void addProduct(Product product);

    @Delete("delete from product where productNum=#{productNum}")
    void delProduct(String productNum);

    @Select("select * from product where productNum=#{productNum}")
    Product findByProductNum(String productNum);

    @Update("update product set productName=#{productName},cityName=#{cityName},departureTime=#{departureTime},productPrice=#{productPrice},productDesc=#{productDesc},productStatus=#{productStatus} where productNum=#{productNum}")
    void updateProduct(Product product);

    @Update("update product set productStatus=#{productStatus} where productNum=#{productNum}")
    void changeStatus(Map map);

    @Select("<script>select * from product" +
            "<where>" +
            "<if test=\"keyword != null and keyword.length()>0\">and productName like #{keyword}</if>" +
            "</where></script>")
    List<Product> findByKeyord(@Param("keyword")String keyword);


}
