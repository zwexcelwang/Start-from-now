package zui.dao;

import zui.beans.User;
import zui.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
操作数据库中User表的类
 */
public class UserDao {
    //声明JDBCTemplate对象公用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    /**
     * 登录方法
     * @param loginUser 只有用户名和密码
     * @return user包含用户全部数据
     */
    public User login(User loginUser) throws DataAccessException, EmptyResultDataAccessException{
        try {
            //1.编写sql方法
            String sql = "select * from user where username=? and password=?";
            //2.调用Query方法
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(),
                    loginUser.getPassword());
            return user;

        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return null;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }

    }
}
