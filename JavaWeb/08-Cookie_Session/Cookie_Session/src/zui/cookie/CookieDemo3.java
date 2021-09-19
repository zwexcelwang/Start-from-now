package zui.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookieDemo3")
public class CookieDemo3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1. 创建cookie对象
        Cookie cookie = new Cookie("msg", "hello");
        //将cookie持久化存储到硬盘，设置cookie的存活时间，30s后会自动删除cookie文件
//        cookie.setMaxAge(30);
//        cookie.setMaxAge(-1);
        cookie.setMaxAge(0);
        //2. 发送cookie对象
        resp.addCookie(cookie);
    }
}
