package zui.service.impl;


import zui.dao.UserDao;
import zui.dao.impl.UserDaoImpl;
import zui.domain.PageBean;
import zui.domain.User;
import zui.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    public List<User> findAll() {
        //调用Dao完成查询
        return dao.findAll();
    }

    public User login(User user) {
        return dao.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    @Override
    public void deleteUser(String id) {
        dao.delete(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public User findUserById(String id) {
        return dao.findById(Integer.parseInt(id));
    }

    @Override
    public void deleteUsers(String[] ids) {
        if(ids != null && ids.length >0) {
            for (String id : ids) {
                dao.delete(Integer.parseInt(id));
            }
        }
    }

    /**
     * 分页查询
     * @param _currentPage
     * @param _rows
     * @return
     */
    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        Integer currentPage = Integer.parseInt(_currentPage);
        Integer rows = Integer.parseInt(_rows);
        if(currentPage <=0) {
            currentPage=1;
        }
        PageBean<User> pageBeans = new PageBean<User>();
        int totalCount = dao.findTotalCount(condition);
        pageBeans.setTotalCount(totalCount);
        int totalPage = totalCount % rows == 0 ?  totalCount/rows : totalCount/rows+1;
        pageBeans.setTotalPage(totalPage);
        if(currentPage >totalPage) {
            currentPage=totalPage;
        }
        int start = (currentPage-1) * rows;
        List<User> list = dao.findByPage(start, rows, condition);
        pageBeans.setList(list);
        pageBeans.setCurrentPage(currentPage);
        pageBeans.setRows(rows);
        System.out.println(pageBeans);
        return pageBeans;
    }





}
