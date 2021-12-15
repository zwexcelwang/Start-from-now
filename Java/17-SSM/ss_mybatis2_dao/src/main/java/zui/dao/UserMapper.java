package zui.dao;

import zui.domain.User;

import java.io.IOException;
import java.util.List;

public interface UserMapper {

    public List<User> findAll() throws IOException;
    public User findUserById(int id);
}
