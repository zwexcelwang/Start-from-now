package zui.dao;

import zui.domain.Category;

import java.util.List;

public interface ICategoryDao {

    /**
     * 查询所有
     * @return
     */
    public List<Category> findAll();
}
