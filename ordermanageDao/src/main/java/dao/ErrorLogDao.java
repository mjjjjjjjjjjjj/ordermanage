package dao;

import domain.ErrorLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ErrorLogDao {

    @Insert("insert into errorlog values (#{id},#{visitTime},#{username},#{ip},#{url},#{method},#{errorCode})")
    void addErrorLog(ErrorLog log);

    @Select("select * from errorlog")
    List<ErrorLog> allErrorLogs();

    @Select("select * from errorlog order by visitTime desc limit 0,1")
    ErrorLog findNewErrorLog();

    @Update("update errorlog set errorCode = #{code} where id = #{id}")
    void addCode(@Param("code") String code,@Param("id") String id);
}
