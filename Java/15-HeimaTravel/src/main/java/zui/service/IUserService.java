package zui.service;

import zui.domain.User;

public interface IUserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean register(User user);

    boolean active(String code);

    User login(User user);
}
