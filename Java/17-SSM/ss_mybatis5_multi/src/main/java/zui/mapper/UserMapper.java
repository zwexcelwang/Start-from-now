package zui.mapper;

import zui.domain.User;

import java.util.List;

public interface UserMapper {
    public List<User> findAll();

    public List<User> findUserAndRoleAll();
}
