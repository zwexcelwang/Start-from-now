package zui;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zui.domain.Account;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JdbcTemplateCRUDTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testUpdate(){
        String sql = "insert into account values (?, ?)";
        int res = jdbcTemplate.update(sql, "tom3", 4000);
//        String sql = "update account set money=? where name=?";
//        int res = jdbcTemplate.update(sql, 6000, "tom");
        System.out.println(res);  //1
    }

    @Test
    public void testDelete(){
        String sql = "delete from account where name=?";
        int res = jdbcTemplate.update(sql, "tom2");
        System.out.println(res);  //1
    }

    @Test
    public void testQueryAll(){
        String sql = "select * from account";
        List<Account> res = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Account>(Account.class));
        System.out.println(res);  //1
    }

    @Test
    public void testQueryOne(){
        String sql = "select * from account where name=?";
        Account res = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Account>(Account.class), "tom");
        System.out.println(res);  //1
    }

    @Test
    //返回简单类型
    public void testQueryCount(){
        String sql = "select count(*) from account;";
        Long res = jdbcTemplate.queryForObject(sql, Long.class);
        System.out.println(res);  //1
    }

}
