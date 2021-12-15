package zui.mapper;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import zui.domain.Order;
import zui.domain.User;

import java.util.Calendar;
import java.util.List;

public interface OrderMapper {
    @Select("select * from orders where uid=#{uid}")
    public List<Order> findByUid(int uid);

//    两张表一起查的
//    @Select("select *,o.id oid from orders o,user2 u where u.id=o.uid")
//    @Results({
//            @Result(column = "oid",property = "id"),
//            @Result(column = "ordertime", property = "ordertime"),
//            @Result(column = "total", property = "total"),
//            @Result(column = "uid", property = "user.id"),
//            @Result(column = "username", property = "user.username"),
//            @Result(column = "password", property = "user.password")
//    })
    @Select("select * from orders")
    @Results({
            @Result(column = "oid",property = "id"),
            @Result(column = "ordertime", property = "ordertime"),
            @Result(column = "total", property = "total"),
            @Result(
                    property = "user",  //要封装的属性名称
                    column = "uid", //根据那个字段去查询user的数据
                    javaType = User.class, //要封装的实体类型
                    //select属性 代表查询哪个接口获得数据
                    one = @One(select = "zui.mapper.UserMapper.findById")
            )
    })
    public List<Order> findAll();


}
