package zui.dao.impl;

import zui.dao.UserDao;

public class UserDaoImpl implements UserDao {

    public UserDaoImpl(){
        System.out.println("UserDaoImpl被创建了。。");
    }

//    public void init() {
//        System.out.println("UserDaoImpl被init了。。");
//    }
//
//    public void destory() {
//        System.out.println("UserDaoImpl被destory了。。");
//    }

    public void save() {
        System.out.println("save running...");

    }

}
