package zui;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import zui.domain.Order;
import zui.domain.User;
import zui.mapper.OrderMapper;
import zui.mapper.UserMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest3 {

    private UserMapper userMapper;
    @Before
    public void before() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void testFindUserAndOrder(){
        List<User> userList = userMapper.findUserAndOrder();
        for(User user:userList){
            System.out.println(user);
        }
    }

    @Test
    public void testFindUserAndRole(){
        List<User> userList = userMapper.findUserAndRole();
        for(User user:userList){
            System.out.println(user);
        }
    }



}
