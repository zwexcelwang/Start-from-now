package zui.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zui.dao.UserDao;
import zui.service.UserService;
import zui.service.impl.UserServiceImpl;

public class UserController {
    public static void main(String[] args) {
//        UserService userService = new UserServiceImpl();
//        userService.save();

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.save();
    }
}
