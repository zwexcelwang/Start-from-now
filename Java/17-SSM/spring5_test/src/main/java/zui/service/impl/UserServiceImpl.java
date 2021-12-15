package zui.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import zui.dao.RoleDao;
import zui.dao.UserDao;
import zui.domain.Role;
import zui.domain.User;
import zui.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    private RoleDao roleDao;
    public void setRoleDao(RoleDao roleDao){
        this.roleDao = roleDao;
    }

    @Override
    public List<User> list() {
        List<User> userList = userDao.findAll();
//        封装每个User的role数据
        for(User user:userList){
//            获得user 的id
            Long id = user.getId();
//            将id作为参数，查询当前userid对应的role集合数据
            List<Role> roles = roleDao.findRoleByUserId(id);
            user.setRoles(roles);
        }
        return userList;
    }

    @Override
    public void save(User user, Long[] ids) {
//        向sys_user中存数据
        Long userId = userDao.save(user);
//        想sys_user_role中存数据
//        System.out.println(ids);
        userDao.saveUserRoleRe(userId, ids);
    }

    @Override
    public void delete(Long userId) {
        userDao.delete(userId);
    }

    @Override
    public User login(String username, String password) {
        try {
            User user = userDao.findByUsernameAndPwd(username, password);
            return user;
        } catch (EmptyResultDataAccessException e){
            return null;
        }

    }

}
