package zui.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoadListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();
        //读取web.xml里的参数
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");


        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(contextConfigLocation);
        //将Spring的应用上下文存储到ServletContext域中
        servletContext.setAttribute("app", applicationContext);
        System.out.println("Spring 容器创建完毕。。。");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
