package zui.service;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import zui.dao.UserDao;
import zui.dao.UserDaoImpl;
import zui.dao.UserMapper;
import zui.domain.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ServiceDemo {

    public static void main(String[] args) throws IOException {
//        UserDao userDao = new UserDaoImpl();
//        List<User> userList = userDao.findAll();

        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        List<User> userList = sqlSession.selectList("userMapper.findAll");
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findAll();

        User user = userMapper.findUserById(2);

        sqlSession.close();

        System.out.println(userList);
        System.out.println(user);


    }



}
