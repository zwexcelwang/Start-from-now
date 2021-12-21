package zui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import zui.pojo.User;

import java.util.List;

@Mapper

public interface UserMapper extends BaseMapper<User> {

    List<User> findAll();
}
