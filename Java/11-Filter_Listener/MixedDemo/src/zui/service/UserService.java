package zui.service;


import zui.domain.PageBean;
import zui.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的业务接口
 */
public interface UserService {

    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();
    public User login(User user);
    public void addUser(User user);
    public void deleteUser(String id);
    public void updateUser(User user);

    public User findUserById(String id);
    public void deleteUsers(String[] ids);
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition);

}
