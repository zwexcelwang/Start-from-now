package zui.service;

import zui.domain.User;

import java.util.List;

public interface UserService {

    public List<User> list();
    public void save(User user, Long[] ids);
    public void delete(Long userId);
    public User login(String username, String password);
}
