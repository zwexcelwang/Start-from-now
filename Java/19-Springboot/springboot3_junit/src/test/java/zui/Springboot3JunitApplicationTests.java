package zui;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zui.dao.BookDao;

// 如果这个类不在Springboot3JunitApplication所在包极其子包下，就要配置这个
//@SpringBootTest(classes = Springboot3JunitApplication.class)

@SpringBootTest
class Springboot3JunitApplicationTests {
    @Autowired
    private BookDao bookDao;
    @Test
    void contextLoads() {
        System.out.println("testing!!!");
        bookDao.save();
    }

}
