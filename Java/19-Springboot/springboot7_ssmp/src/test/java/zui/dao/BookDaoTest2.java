package zui.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zui.domain.Book;

import javax.swing.*;

@SpringBootTest
public class BookDaoTest2 {

    @Autowired
    private BookDao bookDao;
    @Test
    public void testGetById(){
        System.out.println(bookDao.selectById(1));
    }

    @Test
    public void testSave(){
        Book book = new Book();
        book.setType("机械");
        book.setName("故障诊断");
        book.setDescription("机器累了");
        System.out.println(bookDao.insert(book));
    }
    @Test
    public void testUpdate(){
        Book book = new Book();
        book.setType("机械");
        book.setName("故障诊断");
        book.setDescription("机器累了嗷");
        book.setId(7);
        System.out.println(bookDao.updateById(book));
    }
    @Test
    public void testDelete(){
        System.out.println(bookDao.deleteById(7));
    }
    @Test
    public void testGetAll(){
        System.out.println(bookDao.selectList(null));
    }
    @Test
    public void testGetPage(){
        IPage<Book> page = new Page<>(2, 3);
        System.out.println(bookDao.selectPage(page, null));
        System.out.println(page.getPages());
    }
    @Test
    public void testGetBy(){
//        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like("type", "计算机");
//        System.out.println(bookDao.selectList(queryWrapper));


        LambdaQueryWrapper<Book> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Book::getType, "计算机");
//        String type = "1";
        //if(type!=null) lambdaQueryWrapper.like("type", "计算机");
//        lambdaQueryWrapper.like(type!=null,Book::getType, "计算机");
        System.out.println(bookDao.selectList(lambdaQueryWrapper));
    }


}
