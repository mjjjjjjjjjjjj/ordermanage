package service;

import dao.RoleDao;
import domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public List<Role> findAllR(){
        return roleDao.findAllR();
    }

    public void addRole(Role role){
        role.setId(UUID.randomUUID().toString());
        roleDao.addRole(role);
    }

    public List<Role> findNotRole(String cid){
        return roleDao.findNotRole(cid);
    }

    public Role findRoleById2(String id){
        return roleDao.findRoleById2(id);
    }

    public void changeRole(Role role){
        roleDao.updateRole(role);
    }

    public void addPtoR(String rid,String[] ids){
        for (String pid:ids){
            roleDao.addPtoR(rid,pid);
        }
    }
}
