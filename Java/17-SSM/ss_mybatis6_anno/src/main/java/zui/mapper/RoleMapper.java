package zui.mapper;

import org.apache.ibatis.annotations.Select;
import zui.domain.Role;

import java.util.List;

public interface RoleMapper {

    @Select("select * from user_role ur, role r where ur.role_id=r.id and ur.user_id=#{uid}")
    public List<Role> findByUid(int uid);
}
