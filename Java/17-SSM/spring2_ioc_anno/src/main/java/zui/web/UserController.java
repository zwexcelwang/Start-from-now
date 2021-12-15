package zui.web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zui.config.SpringConfiguration;
import zui.service.UserService;
import zui.service.impl.UserServiceImpl;

public class UserController {
    public static void main(String[] args) {
//        UserService userService = new UserServiceImpl();
//        userService.save();
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        UserService userService = (UserService) applicationContext.getBean(UserService.class);
        userService.save();
    }
}
