package dao;

import domain.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogDao {

    @Insert("insert into logs values (#{id},#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void addLogs(Log log);

    @Select("select * from logs")
    List<Log> findAllLog();
}
