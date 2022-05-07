package com.zui.controller;

import com.zui.pojo.User;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zui.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

//    @Autowired  //本地注入
    /*
        1.从zookeeper注册中心获取userService的访问url
        2.进行远程调用rpc
        3.将结果封装为一个代理对象，给变量赋值
     */
    //消费者设置的timeout会覆盖服务提供者设置的
//    @Reference(timeout = 1000) //远程注入
    @Reference(version = "v1.0")
//    @Reference(version = "v1.0", loadbalance = "random", cluster = "failover")
    private UserService userService;

    @RequestMapping("/sayHello")
    public String sayHello() {
        return userService.sayHello();
    }

//    int i=1;
    @RequestMapping("/find")
    public User find(int id) throws InterruptedException {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    System.out.println(i++);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
        return userService.findUserById(id);
    }
}
