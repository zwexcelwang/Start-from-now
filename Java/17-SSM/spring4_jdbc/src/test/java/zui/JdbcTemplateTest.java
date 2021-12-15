package zui;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.beans.PropertyVetoException;
import java.sql.Connection;

public class JdbcTemplateTest {

    @Test
    public void test1() throws PropertyVetoException {
//        创建数据源对象,知道数据库在哪
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/heima");
        dataSource.setUser("root");
        dataSource.setPassword("zuiyia");
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        //设置数据源
        jdbcTemplate.setDataSource(dataSource);
        String sql = "insert into account values (?, ?)";
        int res = jdbcTemplate.update(sql, "tom", 5000);
        System.out.println(res);  //1
    }

    @Test
//    测试Spring产生JdbcTemplate
    public void test2() throws PropertyVetoException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
        String sql = "insert into account values (?, ?)";
        int res = jdbcTemplate.update(sql, "tom2", 5000);
        System.out.println(res);  //1
    }

}
