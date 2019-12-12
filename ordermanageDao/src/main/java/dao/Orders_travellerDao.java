package dao;

import domain.Orders_Travellers;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface Orders_travellerDao {

    @Insert("insert into orders_traveller values (#{orderId},#{travellerId})")
    void ordersConnectTravellers(Orders_Travellers ot);
}
