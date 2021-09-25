package zui.dao.impl;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import zui.dao.UserDao;
import zui.domain.User;
import zui.utils.JDBCUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        //使用JDBC操作数据库...
        //1.定义sql
        String sql = "select * from user1";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(zui.domain.User.class));

        return users;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select * from user1 where username=? and password=?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void add(User user) {
        String sql = "insert into user1 values(null, ?, ?, ?, ?, ?, ?, null, null);";
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(),
                user.getQq(), user.getEmail());
    }

    @Override
    public void delete(int id) {
        String sql = "delete from user1 where id=?;";
        template.update(sql, id);
    }

    @Override
    public void update(User user) {
        String sql = "update user1 set name=?, gender=?, age=?, address=?, qq=?, email=? where id=?;";
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(),
                user.getQq(), user.getEmail(), user.getId());
    }

    @Override
    public User findById(int id) {
        String sql = "select * from user1 where id=?;";
        return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        String sql = "select count(*) from user1 where 1=1";
        StringBuilder stringBuilder = new StringBuilder(sql);
        Set<String> set = condition.keySet();
        //定义一个参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key:set) {
            //排除分页的条件参数
            if("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            String value = condition.get(key)[0];
            if(value != null && !"".equals(value)) {
                stringBuilder.append(" and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }
        System.out.println(stringBuilder.toString());
        return template.queryForObject(stringBuilder.toString(), Integer.class, params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from user1 where 1=1";
        StringBuilder stringBuilder = new StringBuilder(sql);
        Set<String> set = condition.keySet();
        //定义一个参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key:set) {
            //排除分页的条件参数
            if("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            String value = condition.get(key)[0];
            if(value != null && !"".equals(value)) {
                stringBuilder.append(" and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }
        stringBuilder.append(" limit ?, ?");
        params.add(start);
        params.add(rows);
        System.out.println(stringBuilder.toString());
        sql = stringBuilder.toString();
        return template.query(sql, new BeanPropertyRowMapper<User>(User.class), params.toArray());

    }


}