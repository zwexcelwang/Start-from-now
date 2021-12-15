package zui;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zui.dao.UserDao;

public class SpringTest {

    @Test
    public void test1(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao1 = (UserDao) applicationContext.getBean("userDao");
        UserDao userDao2 = (UserDao) applicationContext.getBean("userDao");
        System.out.println(userDao1);
        System.out.println(userDao2);

    }

    @Test
    public void test2(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao1 = (UserDao) applicationContext.getBean("userDao");
        System.out.println(userDao1);
//        applicationContext.close();
    }
}
