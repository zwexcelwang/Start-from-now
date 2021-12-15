package zui.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import zui.dao.UserDao;
import zui.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<User> findAll() {
        String sql = "select * from sys_user;";
        List<User> userList =  jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return userList;
    }

    @Override
    public Long save(User user) {
        String sql = "insert into sys_user values(?,?,?,?,?);";
//        jdbcTemplate.update(sql, null, user.getUsername(), user.getEmail(), user.getPassword(), user.getPhoneNum());
//        创建PreparedStatementCreator
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//                使用原始jdbc完成一个PreparedStatement的组建
                PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setObject(1,null);
                preparedStatement.setString(2,user.getUsername());
                preparedStatement.setString(3,user.getEmail());
                preparedStatement.setString(4,user.getPassword());
                preparedStatement.setString(5,user.getPhoneNum());
                return preparedStatement;
            }
        };
//        创建keyHolder
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator,keyHolder);
        //获得生成的主键
        long userId = keyHolder.getKey().longValue();
        System.out.println(userId);
        return userId; //返回当前保存用户的id 该id是数据库自动生成的
    }

    @Override
    public void saveUserRoleRe(Long userId, Long[] roleIds) {
        String sql = "insert into sys_user_role values(?,?);";
        for(long roleId:roleIds){
            jdbcTemplate.update(sql, userId, roleId);
        }
    }

    @Override
    public void delete(Long userId) {
        String sql = "delete from sys_user where id=?";
        jdbcTemplate.update(sql,userId);
    }

    @Override
    public User findByUsernameAndPwd(String username, String password) throws EmptyResultDataAccessException {
        String sql = "select * from sys_user where username=? and password=?";
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        return user;
    }
}
