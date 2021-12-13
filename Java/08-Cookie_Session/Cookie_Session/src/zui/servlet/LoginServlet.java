package zui.servlet;
import zui.dao.UserDao;
import zui.beans.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
//        Map<String, String[]> map = req.getParameterMap();
//        User loginUser = new User();
//        //使用BeanUtils封装
//        try {
//            BeanUtils.populate(loginUser, map);
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String checkCode = req.getParameter("checkCode");

        //先判断验证码是否正确
        HttpSession httpSession = req.getSession();
        String sessionCheckCode = (String) httpSession.getAttribute("checkCode");
        System.out.println("checkcode in session:" + sessionCheckCode);
        System.out.println("checkCode:"+ checkCode);
        // 保证验证码是一次性的，页面跳转后都会变
        httpSession.removeAttribute("checkCode");
        //忽略大小写比较
        if(sessionCheckCode!=null && sessionCheckCode.equalsIgnoreCase(checkCode)) {
            System.out.println("====");
            //封装User对象
            User loginUser = new User();
            loginUser.setUsername(username);
            loginUser.setPassword(password);
            //调用UserDao的login方法
            UserDao dao = new UserDao();
            User user = dao.login(loginUser);
            //判断user
            if(user == null) {
                System.out.println("user null");
                //登录失败
                req.setAttribute("LoginError", "用户名或密码错误");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }else {
                System.out.println("user okk");
                // 登录成功，存储数据
                httpSession.setAttribute("user", user);
                //重定向到success.jsp
                resp.sendRedirect(req.getContextPath()+"/success.jsp");
            }
        }else {
            System.out.println("!!!!!=");
            //验证码不一样
            req.setAttribute("CheckCodeError", "验证码错误");
            //转发到登录页面
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }



    }
}
