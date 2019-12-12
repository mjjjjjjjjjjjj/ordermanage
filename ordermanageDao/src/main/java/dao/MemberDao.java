package dao;

import domain.Member;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberDao {
    @Select("select * from member where id = #{memberId}")
    Member findMemberById(String memberId);

    @Select("select * from member")
    List<Member> findAllM();

    @Select("select * from member where id = #{memberId}")
    @Results(id = "memMapper",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property = "travellers",many = @Many(select = "dao.TravellerDao.findTravellerBymid"))
    })
    Member findMember(String id);
}
