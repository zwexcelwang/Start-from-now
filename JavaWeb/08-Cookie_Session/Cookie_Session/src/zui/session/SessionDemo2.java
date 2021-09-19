package zui.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/sessionDemo2")
public class SessionDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //3. 获取session对象
        HttpSession httpSession = req.getSession();
        //4. 获取数据，遍历session
        Object msg = httpSession.getAttribute("msg");
        System.out.println(msg);
        System.out.println(httpSession.getId());
    }
}
