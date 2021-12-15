package zui.dao.impl;

import zui.dao.UserDao;
import zui.domain.User;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class UserDaoImpl implements UserDao {
    private String username;
    private int age;
    private List<String> stringList;
    private Map<String, User> userMap;
    private Properties properties;

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
        System.out.println(this.toString());
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public void setUserMap(Map<String, User> userMap) {
        this.userMap = userMap;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "UserDaoImpl{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", stringList=" + stringList +
                ", userMap=" + userMap +
                ", properties=" + properties +
                '}';
    }
}
