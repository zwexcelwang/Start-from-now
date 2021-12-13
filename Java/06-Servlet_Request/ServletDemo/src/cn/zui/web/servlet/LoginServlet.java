package cn.zui.web.servlet;
import cn.zui.dao.UserDao;
import cn.zui.beans.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("utf-8");
        //获取请求参数
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        //封装User对象
//        User loginUser = new User();
//        loginUser.setUsername(username);
//        loginUser.setPassword(password);
        //简化版本
        Map<String, String[]> map = req.getParameterMap();
        User loginUser = new User();
        //使用BeanUtils封装
        try {
            BeanUtils.populate(loginUser, map);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        //调用UserDao的login方法
        UserDao dao = new UserDao();
        User user = dao.login(loginUser);
        //判断user
        if(user == null) {
            //登录失败
            req.getRequestDispatcher("/failServlet").forward(req, resp);
        }else {
        // 登录成功，存储数据
            req.setAttribute("user", user);
            req.getRequestDispatcher("/successServlet").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
