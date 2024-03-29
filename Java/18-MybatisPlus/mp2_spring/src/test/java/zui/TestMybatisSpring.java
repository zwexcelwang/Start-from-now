package zui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zui.mapper.UserMapper;
import zui.pojo.User;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestMybatisSpring {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList(){
        List<User> userList = this.userMapper.selectList(null);
        for(User user:userList){
            System.out.println(user);
        }
    }

}
