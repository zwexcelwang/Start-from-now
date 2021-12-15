package zui.web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import zui.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        ServletContext servletContext = this.getServletContext();
//        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("app");
//        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext) ;
        System.out.println("userServlet里。。");
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.save();
    }

}
