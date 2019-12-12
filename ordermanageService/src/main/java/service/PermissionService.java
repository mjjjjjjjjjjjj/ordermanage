package service;

import dao.PermissionDao;
import domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    public List<Permission> findAllPermission(){
        return permissionDao.findAllPer();
    }

    public void addPermission(Permission permission){
        permission.setId(UUID.randomUUID().toString());
        permissionDao.addPermission(permission);
    }

    public void changePermission(Permission permission){
        permissionDao.changePermission(permission);
    }

    public void delPermission(String id){
        permissionDao.delPermission(id);
    }

    public List<Permission> findPermission(String rid){
        return permissionDao.findPermission(rid);
    }
}
