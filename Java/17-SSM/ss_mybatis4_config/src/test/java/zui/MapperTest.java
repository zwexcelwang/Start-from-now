package zui;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import zui.domain.User;
import zui.mapper.UserMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MapperTest {

    @Test
    public void test1() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setId(4);
        user.setUsername("tom4");
        user.setPassword("1234");
        user.setBirthday(new Date());
        userMapper.save(user);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test2() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findById(1);
        System.out.println(user.getBirthday());
        System.out.println(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test3() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        设置分页的相关参数, 当前页，每页显示的条数
        PageHelper.startPage(1,3);
        List<User> userList = userMapper.findAll();
        for(User user:userList){
            System.out.println(user);
        }

//        获得与分页相关参数
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        System.out.println("当前页：" + pageInfo.getPageNum());
        System.out.println("下一页：" + pageInfo.getNextPage());
        System.out.println("是否最后一页：" + pageInfo.isIsLastPage());
        System.out.println("每页显示条数：" + pageInfo.getPageSize());
        System.out.println("总数：" + pageInfo.getTotal());

        sqlSession.commit();
        sqlSession.close();
    }



}
