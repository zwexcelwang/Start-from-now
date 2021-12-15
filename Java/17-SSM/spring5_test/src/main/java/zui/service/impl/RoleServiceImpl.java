package zui.service.impl;

import zui.dao.RoleDao;
import zui.domain.Role;
import zui.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;
    public void setRoleDao(RoleDao roleDao){
        this.roleDao = roleDao;
    }
    public List<Role> list() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }
}
