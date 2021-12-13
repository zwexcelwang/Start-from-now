package zui.web;

import zui.service.IUserService;
import zui.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/0activeUserServlet")// 不用了
public class ActiveUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("active user servlet!!!");

        //1.获取激活码
        String code = req.getParameter("code");
        if(code != null){
            //2.调用service完成激活
            IUserService userService = new UserService();
            boolean flag = userService.active(code);

            //3.判断标记
            String msg = null;
            if(flag){
                //激活成功
                msg = "激活成功，请<a href='login.html'>登录</a>";
            }else{
                //激活失败
                msg = "激活失败，请联系管理员!";
            }
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write(msg);
        }



    }
}
