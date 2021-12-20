package zui.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zui.controller.utils.Result;
import zui.domain.Book;
import zui.service.BookService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public Result getAll(){
        return new Result(true, bookService.list());
    }

    @PostMapping("/save")
    public Result save(@RequestBody Book book) throws IOException {
        if("123".equals(book.getName())){throw new IOException();}
        boolean flag = bookService.save(book);
        return new Result(flag, flag?"添加成功":"添加失败");
    }

    @PutMapping
    public Result update(@RequestBody Book book){
        return new Result(bookService.updateById(book));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return new Result(bookService.removeById(id));
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        return new Result(true, bookService.getById(id));
    }

//    @GetMapping("/{currentPage}/{pageSize}")
//    public Result getPage(@PathVariable String currentPage,@PathVariable String  pageSize){
//        IPage<Book> page = bookService.getPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
////        如果当前页码值大于总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
//        if(Integer.parseInt(currentPage)>page.getPages()){
//            page = bookService.getPage((int)page.getPages(), Integer.parseInt(pageSize));
//        }
//        return new Result(true, page);
//    }

    @GetMapping("/{currentPage}/{pageSize}")
    public Result getPage(@PathVariable String currentPage,@PathVariable String  pageSize, Book book){
        IPage<Book> page = bookService.getPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize), book);
//        如果当前页码值大于总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if(Integer.parseInt(currentPage)>page.getPages()){
            page = bookService.getPage((int)page.getPages(), Integer.parseInt(pageSize), book);
        }
        return new Result(true, page);
    }

}
