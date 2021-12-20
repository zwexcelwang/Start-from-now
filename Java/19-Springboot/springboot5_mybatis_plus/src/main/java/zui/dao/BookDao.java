package zui.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import zui.domain.Book;

@Mapper
public interface BookDao extends BaseMapper<Book> {

}
