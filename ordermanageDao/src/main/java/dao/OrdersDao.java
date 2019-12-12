package dao;

import domain.Orders;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDao {

    @Select("select * from orders")
    @Results(id = "orderMapper",value = {
            @Result(id = true,column = "orderNum",property = "orderNum"),
            @Result(column = "productId",property = "product",one = @One(select = "dao.ProductDao.findByProductId"))
    })
    List<Orders> findAllOrders();

    @Select("select * from orders where id = #{id}")
    @Results(id = "orderMapper2",value = {
            @Result(id = true,column = "orderNum",property = "orderNum"),
            @Result(column = "productId",property = "product",one = @One(select = "dao.ProductDao.findByProductId")),
            @Result(column = "memberId",property = "member",one = @One(select = "dao.MemberDao.findMemberById")),
            @Result(column = "id",property = "travellers",many = @Many(select = "dao.TravellerDao.findTravellerById"))
    })
    Orders findOdersDetail(String id);

    @Insert("insert into orders values (#{id},#{orderNum},#{orderTime},#{peopleCount},#{orderDesc},#{payType},#{orderStatus},#{product.id},#{member.id});")
    void addOrders(Orders orders);

    @Select("SELECT * FROM orders WHERE orderStatus = 0")
    List<Orders> findNoPay();

    @Delete("delete from orders where id = #{id}")
    void delOrder(@Param("id") String id);

    @Update("update orders set orderStatus = 1 where id = #{id}")
    void payOrders(@Param("id") String id);
}
