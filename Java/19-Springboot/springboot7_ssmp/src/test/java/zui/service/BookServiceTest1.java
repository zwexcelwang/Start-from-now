package zui.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zui.domain.Book;

@SpringBootTest
public class BookServiceTest1 {

    @Autowired BookService1 bookService1;
    @Test
    public void testSave(){
        Book book = new Book();
        book.setType("机械");
        book.setName("寿命预测");
        book.setDescription("能用多久");
        System.out.println(bookService1.save(book));
    }

    @Test
    public void testUpdate(){
        Book book = new Book();
        book.setType("机械");
        book.setName("寿命预测");
        book.setDescription("用不了多久");
        book.setId(8);
        System.out.println(bookService1.update(book));
    }
    @Test
    public void testDelete(){
        System.out.println(bookService1.delete(8));
    }
    @Test
    public void testGetById(){
        System.out.println(bookService1.getById(8));
    }
    @Test
    public void testGetAll(){
        System.out.println(bookService1.getAll());
    }

    @Test
    public void testGetPage(){
        System.out.println(bookService1.getPage(2, 3));
    }
}

