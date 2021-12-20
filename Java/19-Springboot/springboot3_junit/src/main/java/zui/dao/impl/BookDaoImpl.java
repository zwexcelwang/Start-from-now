package zui.dao.impl;

import org.springframework.stereotype.Repository;
import zui.dao.BookDao;

@Repository
public class BookDaoImpl implements BookDao {
    @Override
    public void save() {
        System.out.println("book dao is running ...");
    }
}
