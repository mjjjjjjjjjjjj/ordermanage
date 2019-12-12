package dao;

import domain.Traveller;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravellerDao {

    @Select("select * from traveller where id in (select travellerId from orders_traveller where orderId = #{id})")
    List<Traveller> findTravellerById(String id);

    @Select("select * from traveller")
    List<Traveller> findAllT();

    @Select("select * from traveller where id = #{id}")
    Traveller findByTravellerId(@Param("id")String id);

    @Select("select * from traveller where mid = #{mid} ")
    List<Traveller> findTravellerBymid(String mid);
}
