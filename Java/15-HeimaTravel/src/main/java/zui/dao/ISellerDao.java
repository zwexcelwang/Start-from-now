package zui.dao;

import zui.domain.Seller;

public interface ISellerDao {
    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Seller findById(int id);
}
