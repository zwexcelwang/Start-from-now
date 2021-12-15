package zui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zui.config.SpringConfiguration;
import zui.service.UserService;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext.xml")
@ContextConfiguration(classes = {SpringConfiguration.class})
public class SpringJunitTest {
    @Autowired
    private UserService userService;

    @Autowired
    private DataSource dataSource;
    @Test
    public void test1(){
        userService.save();
    }

    @Test
    public void test2() throws SQLException {
        System.out.println(dataSource.getConnection());
    }
}
