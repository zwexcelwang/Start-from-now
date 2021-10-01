package zui.web.servlet;

import org.apache.commons.beanutils.BeanUtils;
import zui.domain.User;
import zui.service.UserService;
import zui.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
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
        //1. 设置编码
        req.setCharacterEncoding("utf-8");
        //2. 获取数据
        //2.1 获取用户填写验证码
        String verifycode = req.getParameter("verifycode");
        Map<String, String[]> map = req.getParameterMap();

        //3. 验证码校验
        String checkCodeInSession = (String) req.getSession().getAttribute("checkCodeInSession");
        //保证一次性
        req.getSession().removeAttribute("checkCodeInSession");
        if(!checkCodeInSession.equalsIgnoreCase(verifycode)){
            req.setAttribute("login_msg", "验证码错误!");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }
        //4. 封装User对象
        User user =  new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(user.toString());
        //5. 调用Service查询
        UserService userService = new UserServiceImpl();
        User loginUser = userService.login(user);
        //6. 判断是否登录成功
        if(loginUser != null) {
            // 登录成功，就将用户存入session
            HttpSession session = req.getSession();
            session.setAttribute("user", loginUser);
            resp.sendRedirect(req.getContextPath()+"/index.jsp");
        }else {
            req.setAttribute("login_msg", "用户名或密码错误");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
