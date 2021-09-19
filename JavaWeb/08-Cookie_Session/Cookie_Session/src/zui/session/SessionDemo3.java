package zui.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.concurrent.Callable;

@WebServlet("/sessionDemo3")
public class SessionDemo3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1. 创建session对象
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("msg", "session msg");
        //期望客户端关闭后，session也能相同
        Cookie cookie = new Cookie("JSESSIONID", httpSession.getId());
        cookie.setMaxAge(60*60);    //一个小时
        resp.addCookie(cookie);

    }
}
