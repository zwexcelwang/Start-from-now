package zui.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import zui.domain.Book;


public interface BookService extends IService<Book> {

    IPage<Book> getPage(Integer currentPage, Integer pageSize);

    IPage<Book> getPage(Integer currentPage, Integer pageSize, Book book);
}
