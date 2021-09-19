<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: USTC
  Date: 2021/9/19
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Last Time</title>
</head>
<body>
    <%
        response.setContentType("text/html; charset=utf-8");
        boolean flag = false;
        Cookie[] cookies = request.getCookies();
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
//                    response.getWriter().write("<h1>欢迎回来，您上次的访问时间为" + value + "</h1>");
                    out.write("<h1>欢迎回来，您上次的访问时间为" + value + "</h1>");

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
                    response.addCookie(cookie);
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
            response.addCookie(cookie);
//            response.getWriter().write("<h1>您好，欢迎您首次访问"+ "</h1>");
            out.write("<h1>您好，欢迎您首次访问"+ "</h1>");
        }
    %>
</body>
</html>
