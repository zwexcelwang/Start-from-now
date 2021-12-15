package zui.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import zui.dao.RoleDao;
import zui.domain.Role;

import java.util.List;

public class RoleDaoImpl implements RoleDao {

    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Role> findAll() {
        String sql = "select * from sys_role;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Role>(Role.class));
    }

    @Override
    public void save(Role role) {
        String sql = "insert into sys_role values(?,?,?);";
        jdbcTemplate.update(sql, null, role.getRoleName(), role.getRoleDesc());
    }

    @Override
    public List<Role> findRoleByUserId(Long id) {
        String sql = "select sys_role.id, sys_role.roleName, sys_role.roleDesc from sys_role, sys_user_role where sys_role.id = sys_user_role.roleId and sys_user_role.userId=?;";
        List<Role> roleList = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Role>(Role.class), id);
        return roleList;

    }
}
