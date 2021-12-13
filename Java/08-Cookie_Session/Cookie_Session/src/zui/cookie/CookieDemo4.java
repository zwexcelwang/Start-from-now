package zui.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/cookieDemo4")
public class CookieDemo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=utf-8");
        boolean flag = false;
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("lastTime".equals(name)) {
                    flag = true;
                    String value = cookie.getValue();
                    System.out.println("解码前：" + value);
                    //URL解码
                    value = URLDecoder.decode(value, "utf-8");
                    System.out.println("解码后：" + value);
                    resp.getWriter().write("<h1>欢迎回来，您上次的访问时间为" + value + "</h1>");
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
                    String strDate = simpleDateFormat.format(date);
                    System.out.println("编码前：" + strDate);
                    //URL编码
                    strDate = URLEncoder.encode(strDate, "utf-8");
                    System.out.println("编码后：" + strDate);
                    cookie.setValue(strDate);
                    cookie.setMaxAge(60 * 60 * 24 * 30);    //一个月
//                    cookie.setMaxAge(0);    //一个月
                    resp.addCookie(cookie);
                    break;
                }
            }
        }
        System.out.println(flag);
        if(!flag) {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String strDate = simpleDateFormat.format(date);
            System.out.println("编码前：" + strDate);
            //URL编码
            strDate = URLEncoder.encode(strDate, "utf-8");
            System.out.println("编码后：" + strDate);
            Cookie cookie = new Cookie("lastTime", strDate);
            cookie.setMaxAge(60 * 60 * 24 * 30);    //一个月
            resp.addCookie(cookie);
            resp.getWriter().write("<h1>您好，欢迎您首次访问"+ "</h1>");
        }
    }
}