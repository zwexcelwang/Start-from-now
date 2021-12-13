package zui.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import javax.servlet.annotation.WebListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

//@WebListener
public class ContextLoaderListener implements ServletRequestListener {
    /**
     * 服务器正常关闭，调用该方法
     * @param servletRequestEvent
     */
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        System.out.println("ServletContext对象被销毁了");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        System.out.println("ServletContext对象被创建了");
        //加载资源文件
        //获取ServletContext
        ServletContext servletContext = servletRequestEvent.getServletContext();
        //加载资源文件
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        //获取真实路径
        String realPath = servletContext.getRealPath(contextConfigLocation);
        //加载进内存
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(realPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(fileInputStream);

    }
}
