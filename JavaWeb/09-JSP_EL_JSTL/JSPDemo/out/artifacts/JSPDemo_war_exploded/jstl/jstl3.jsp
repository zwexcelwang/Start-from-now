<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="zui.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%

        List list = new ArrayList();
        list.add(new User("zhangsan", 12, new Date()));
        list.add(new User("lisi", 13, new Date()));
        list.add(new User("wangwu", 14, new Date()));
        request.setAttribute("list", list);
    %>

    <table>
        <tr>
            <td>编号</td>
            <td>姓名</td>
            <td>年龄</td>
            <td>生日</td>
        </tr>

<%--        数据行--%>
        <c:forEach items="${list}" var="user" varStatus="s">
            <c:if test="${s.count % 2 ==0}">
                <tr bgcolor="#f5f5dc">
                    <td>${s.count}</td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>${user.birthdayStr}</td>
                </tr>
            </c:if>
            <c:if test="${s.count % 2 ==1}">
                <tr bgcolor="#8fbc8f">
                    <td>${s.count}</td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>${user.birthdayStr}</td>
                </tr>
            </c:if>

        </c:forEach>
    </table>

</body>
</html>
