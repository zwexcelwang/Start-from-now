package zui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import zui.pojo.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<User> findAll();
}
