package dao;

import domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {

    @Select("select * from role where id in (select rid from customer_role where cid = #{cid})")
    @Results(id = "rMapper",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property = "permissions",many = @Many(select = "dao.PermissionDao.findById"))
    })
    List<Role> findRoleById(String cid);

    @Select("select * from role")
    List<Role> findAllR();

    @Insert("insert into role values(#{id},#{roleName},#{roleDesc})")
    void addRole(Role role);

    @Select("select * from role where id not in (select rid from customer_role where cid = #{cid})")
    List<Role> findNotRole(String cid);

    @Select("select * from role where id = #{cid}")
    @Results(id = "rMapper2",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property = "permissions",many = @Many(select = "dao.PermissionDao.findById"))
    })
    Role findRoleById2(@Param("cid") String cid);

    @Update("update role set roleName = #{roleName},roleDesc = #{roleDesc} where id = #{id}")
    void updateRole(Role role);

    @Insert("insert into role_permission values (#{rid},#{pid})")
    void addPtoR(@Param("rid") String rid,@Param("pid") String pid);

}
