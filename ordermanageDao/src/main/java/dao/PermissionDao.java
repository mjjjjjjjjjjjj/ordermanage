package dao;

import domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDao {

    @Select("select * from permission where id in (select pid from role_permission where rid = #{id})")
    List<Permission> findById(String id);

    @Select("select * from permission")
    List<Permission> findAllPer();

    @Insert("insert into permission values(#{id},#{permissionName},#{url})")
    void addPermission(Permission permission);

    @Update("update permission set permissionName=#{permissionName},url=#{url} where id = #{id}")
    void changePermission(Permission permission);

    @Delete("delete from permission where id = #{id}")
    void delPermission(String id);

    @Select("select * from permission where id not in(select pid from role_permission where rid = #{rid})")
    List<Permission> findPermission(String rid);
}
