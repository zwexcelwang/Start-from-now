package zui;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import zui.domain.Order;
import zui.domain.User;
import zui.mapper.OrderMapper;
import zui.mapper.UserMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MapperTest {

    @Test
    public void test1() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        List<Order> orderList = orderMapper.findAll();
        System.out.println(orderList);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test2() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findAll();
        System.out.println(userList);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test3() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findUserAndRoleAll();
        System.out.println(userList);
        sqlSession.commit();
        sqlSession.close();
    }



}
