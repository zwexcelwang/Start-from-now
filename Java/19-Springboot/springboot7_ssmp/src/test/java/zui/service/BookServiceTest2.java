package zui.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zui.domain.Book;

@SpringBootTest
public class BookServiceTest2 {

    @Autowired BookService bookService;
    @Test
    public void testSave(){
        Book book = new Book();
        book.setType("机械");
        book.setName("寿命预测2");
        book.setDescription("能用多久2");
        System.out.println(bookService.save(book));
    }

    @Test
    public void testUpdate(){
        Book book = new Book();
        book.setType("机械");
        book.setName("寿命预测");
        book.setDescription("用不了多久2");
        book.setId(9);
        System.out.println(bookService.updateById(book));
    }
    @Test
    public void testDelete(){
        System.out.println(bookService.removeById(9));
    }
    @Test
    public void testGetById(){
        System.out.println(bookService.getById(9));
    }
    @Test
    public void testGetAll(){
        System.out.println(bookService.list());
    }

    @Test
    public void testGetPage(){
        IPage<Book> page = new Page<>(2, 3);
        System.out.println(bookService.page(page, null));
    }
}

