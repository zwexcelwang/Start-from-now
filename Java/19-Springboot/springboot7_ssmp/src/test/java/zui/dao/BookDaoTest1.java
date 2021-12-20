package zui.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookDaoTest1 {

    @Autowired
    private BookDao1 bookDao1;
    @Test
    public void testGetById(){
        System.out.println(bookDao1.getById(1));
    }
}
