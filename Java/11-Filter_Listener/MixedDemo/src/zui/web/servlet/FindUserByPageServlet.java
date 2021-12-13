package zui.web.servlet;

import zui.domain.PageBean;
import zui.domain.User;
import zui.service.UserService;
import zui.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String currentPage = req.getParameter("currentPage");
        String rows = req.getParameter("rows");
        if(currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        if(rows == null || "".equals(rows)) {
            rows = "5";
        }

        Map<String, String[]> condition = req.getParameterMap();
        UserService userService = new UserServiceImpl();
        PageBean<User> pageBeans= userService.findUserByPage(currentPage, rows, condition);
        System.out.println(pageBeans);
        req.setAttribute("pageBeans", pageBeans);
        req.setAttribute("condition", condition);
        req.getRequestDispatcher("/list.jsp").forward(req, resp);
    }
}
