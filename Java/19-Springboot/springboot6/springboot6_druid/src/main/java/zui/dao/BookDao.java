package zui.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import zui.domain.Book;

@Mapper
public interface BookDao {
    @Select("select * from book where id = #{id}")
    public Book getById(Integer id);
}
