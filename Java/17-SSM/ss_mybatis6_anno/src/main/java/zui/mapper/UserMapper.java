package zui.mapper;


import org.apache.ibatis.annotations.*;
import zui.domain.Order;
import zui.domain.User;

import java.util.List;

public interface UserMapper {
    @Insert("insert into user2 values (#{id}, #{username}, #{password}, #{birthday})")
    public void save(User user);

    @Update("update user2 set username=#{username}, password=#{password} where id=#{id}")
    public void update(User user);

    @Delete("delete from user2 where id=#{idd}")
    public void delete(User user);

    @Select("select * from user2 where id=#{idd}")
    public User findById(int id);

    @Select("select * from user2")
    public List<User> findAll();

    @Select("select * from user2")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(
                    property = "orderList",
                    javaType = List.class,
                    column = "id",
                    many = @Many(select = "zui.mapper.OrderMapper.findByUid")
            )
    })
    public List<User> findUserAndOrder();


    @Select("select * from user2")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(
                    property = "roleList",
                    javaType = List.class,
                    column = "id",
                    many = @Many(select = "zui.mapper.RoleMapper.findByUid")
            )

    })
    public List<User> findUserAndRole();
}
