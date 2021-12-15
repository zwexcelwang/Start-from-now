package zui.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import zui.dao.UserDao;
import zui.service.UserService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

//<bean id="userSerive" class="zui.service.impl.UserServiceImpl">
//    <property name="userDao" ref="userDao"></property>
//</bean>
//@Component("userService")
@Service("userService")
@Scope("singleton")
public class UserServiceImpl implements UserService {
//    <property name="userDao" ref="userDao"></property>
//    @Autowired  //按照数据类型从Spring容器中进行匹配的
//    @Qualifier("userDao") //按照id名称从Spring容器中进行匹配的，但是此次的Qualifier要结合Autowired一起使用
    @Resource(name = "userDao") //@Resource相当于@Atuowired+@Qualifier
    private UserDao userDao;

    @Value("${jdbc.driver}")
    private String driver;
    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    //    set方式注入
//    public void setUserDao(UserDao userDao){
//        this.userDao = userDao;
//    }
    @Override
    public void save() {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        userDao.save();
        System.out.println(driver);
    }

    @PostConstruct
    public void init(){
        System.out.println("init...");
    }

    @PreDestroy
    public void destory(){
        System.out.println("destory...");
    }
}
