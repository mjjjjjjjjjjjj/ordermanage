package service;

import dao.MemberDao;
import domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberDao memberDao;

    public Member findMemberById(String memberId){
        return memberDao.findMemberById(memberId);
    }

    public List<Member> findAllM(){
        return memberDao.findAllM();
    }

    public Member findMember(String id){
        return memberDao.findMember(id);
    }
}
