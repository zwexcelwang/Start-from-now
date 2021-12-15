package zui.dao;

import zui.domain.Role;

import java.util.List;

public interface RoleDao {
    public List<Role> findAll();
    public void save(Role role);
    public List<Role> findRoleByUserId(Long id);

}
