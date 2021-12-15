package zui.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zui.dao.UserDao;
import zui.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl() {
    }

//    public UserServiceImpl(UserDao userDao) {
//        this.userDao = userDao;
//    }

    //    set方式注入
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
    @Override
    public void save() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        userDao.save();
    }
}
