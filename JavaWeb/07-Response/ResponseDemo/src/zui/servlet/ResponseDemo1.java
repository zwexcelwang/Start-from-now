package zui.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/responseDemo1")
public class ResponseDemo1 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo1...");
        /*
        //访问responseDemo1会自动跳转到responseDemo2资源
        //设置状态码302
        resp.setStatus(302);
        // 设置响应头location
        resp.setHeader("location", "/demo2/responseDemo2");
         */

        //简化的重定向方法
        //动态获取虚拟目录
        String contextPath = req.getContextPath();

        resp.sendRedirect(contextPath + "/responseDemo2");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
