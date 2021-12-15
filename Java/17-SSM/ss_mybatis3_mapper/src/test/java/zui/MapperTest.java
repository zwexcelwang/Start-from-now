package zui;

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
import java.util.List;

public class MapperTest {

    @Test
    public void test1() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

//        User user = new User();
//        user.setId(2);
//        user.setUsername("mymy1");
//        List<User> userList = userMapper.findByCondition(user);

        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        List<User> userList = userMapper.findByIds(ids);

        System.out.println(userList);
    }
}
