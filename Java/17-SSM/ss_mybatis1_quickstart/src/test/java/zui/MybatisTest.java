package zui;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import zui.domain.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    @Test
    public void test1() throws IOException {
//        获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
//        获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        执行操作，参数：namespace+id
        List<User> userList = sqlSession.selectList("userMapper.findAll");

        System.out.println(userList);

//        释放资源
        sqlSession.close();

    }

    @Test
    public void test2() throws IOException {
        User user = new User();
        user.setUsername("mymy");
        user.setPassword("1234");
//        获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
//        获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        执行操作，参数：namespace+id
        sqlSession.insert("userMapper.save", user);
        System.out.println();
//        mybatis执行更新操作，要提交事务，它默认事务不提交的
        sqlSession.commit();
//        释放资源
        sqlSession.close();

    }

    @Test
    public void test3() throws IOException {
        User user = new User();
        user.setId(2);
        user.setUsername("mymy1");
        user.setPassword("1234");
//        获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
//        获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        执行操作，参数：namespace+id
        sqlSession.update("userMapper.update", user);
        System.out.println();
//        mybatis执行更新操作，要提交事务，它默认事务不提交的
        sqlSession.commit();
//        释放资源
        sqlSession.close();

    }

    @Test
    public void test4() throws IOException {
//        获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
//        获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        执行操作，参数：namespace+id
        sqlSession.delete("userMapper.delete", 2);
        System.out.println();
//        mybatis执行更新操作，要提交事务，它默认事务不提交的
        sqlSession.commit();
//        释放资源
        sqlSession.close();
    }


    @Test
    public void test5() throws IOException {
//        获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
//        获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        执行操作，参数：namespace+id
        User user = sqlSession.selectOne("userMapper.findUserById", 2);
        System.out.println(user);
//        mybatis执行更新操作，要提交事务，它默认事务不提交的
        sqlSession.commit();
//        释放资源
        sqlSession.close();
    }


}
