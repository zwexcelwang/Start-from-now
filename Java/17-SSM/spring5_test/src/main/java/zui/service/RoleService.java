package zui.service;

import zui.domain.Role;

import java.util.List;

public interface RoleService {

    public List<Role> list();
    public void save(Role role);
}
