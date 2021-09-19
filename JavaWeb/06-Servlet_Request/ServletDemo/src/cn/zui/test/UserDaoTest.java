package cn.zui.test;

import cn.zui.dao.UserDao;
import cn.zui.beans.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void testLogin() {
        User loginUser = new User();
        loginUser.setUsername("admin");
        loginUser.setPassword("1223");
        UserDao userDao = new UserDao();
        userDao.login(loginUser);

        System.out.println(loginUser.toString());
    }
}
