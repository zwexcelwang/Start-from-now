package com.zui.service.impl;

import com.zui.pojo.User;
import com.zui.service.UserService;
import org.apache.dubbo.config.annotation.Service;

//@Service //将该类的对象创建出来，放到spring的ioc容器中  bean定义
//将这个类提供的方法（服务）对外发布，将访问的地址ip，端口。路径注册到注册中心
//@Service(timeout = 3000, retries = 0)  //当前服务超时时间3秒，重试0
@Service(version = "v1.0", weight = 100)
public class UserServiceImpl implements UserService {
    @Override
    public String sayHello() {
        return "hello dubbo~";
    }

//    int i=1;
    @Override
    public User findUserById(int id) {
        User user = new User(1, "zhangsan", "123456");
        System.out.println("old...");
//        System.out.println("服务被调用了：" + (i++) + "次");
        //数据库查询很慢，查了5秒
//        try {
//            Thread.sleep(5000);
//        }catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        return user;
    }
}
