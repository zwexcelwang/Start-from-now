package com.zui.service;

import com.zui.pojo.User;

public interface UserService {

    public String sayHello();

    public User findUserById(int id) throws InterruptedException;
}
