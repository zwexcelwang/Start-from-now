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

public class MyBatisTest2 {

    private OrderMapper orderMapper;
    @Before
    public void before() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        orderMapper = sqlSession.getMapper(OrderMapper.class);
    }

    @Test
    public void testFindAll(){
        List<Order> orderList = orderMapper.findAll();
        System.out.println(orderList);
    }



}
