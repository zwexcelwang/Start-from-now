package zui.dao;

import zui.domain.User;

import java.io.IOException;
import java.util.List;

public interface UserDao {

    public List<User> findAll() throws IOException;
}
