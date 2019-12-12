package dao;

import domain.Customer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerDao {

    @Select("select * from customer where username = #{username}")
    @Results(id = "customerMapper",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property = "roles",many=@Many(select = "dao.RoleDao.findRoleById"))
    })
    Customer findCustomerByName(String username);

    @Select("select * from customer")
    List<Customer> findAllC();

    @Insert("insert into customer values (#{id},#{username},#{email},#{password},#{phoneNum},#{status})")
    void addCustomer(Customer customer);

    @Select("select * from customer where id = #{id}")
    @Results(id = "Mapper2",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property = "roles",many=@Many(select = "dao.RoleDao.findRoleById"))
    })
    Customer findCustomerById(String id);

    @Insert("insert into customer_role values (#{cid},#{rid})")
    void addRoleToCustomer(@Param("cid") String cid,@Param("rid") String rid);
}
