package zui.dao;

import zui.domain.User;

import java.util.List;

public interface UserDao {
    public List<User> findAll();

    public Long save(User user);

    public void saveUserRoleRe(Long userId, Long[] roleIds);

    public void delete(Long userId);

    public User findByUsernameAndPwd(String username, String password);
}
