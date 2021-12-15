package zui;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import zui.domain.User;
import zui.mapper.UserMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {

    private UserMapper userMapper;
    @Before
    public void before() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setId(5);
        user.setUsername("tom5");
        user.setPassword("135");

        userMapper.save(user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(6);
        user.setUsername("tom6");
        user.setPassword("123");

        userMapper.update(user);
    }

    @Test
    public void testFindAll(){
        List<User> userList = userMapper.findAll();
        System.out.println(userList);
    }



}
