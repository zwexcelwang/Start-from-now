package zui.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import zui.domain.Book;

@Mapper
public interface BookDao1 {
    @Select("select * from book where id=#{id}")
    Book getById(Integer id);
}
