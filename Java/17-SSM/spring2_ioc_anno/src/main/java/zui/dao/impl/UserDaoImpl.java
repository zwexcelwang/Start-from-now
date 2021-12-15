package zui.dao.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import zui.dao.UserDao;

//<bean id="userDao" class="zui.zui.dao.impl.UserDaoImpl"></bean>
//@Component("userDao")
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    public UserDaoImpl(){
        System.out.println("UserDaoImpl被创建了。。");
    }


    public void save() {
        System.out.println("save running...");

    }
}
