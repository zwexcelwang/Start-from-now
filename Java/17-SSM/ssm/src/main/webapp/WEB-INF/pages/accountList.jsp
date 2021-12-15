<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>展示账户数据</title>
</head>
<body>
    <table>
        <thead>
            <tr>
                <td>账户id</td>
                <td>账户名称</td>
                <td>账户金额</td>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${accountList}" var="account">
            <tr>
                <td>${account.id}</td>
                <td>${account.name}</td>
                <td>${account.money}</td>
            </tr>
        </c:forEach>
<%--            <tr>--%>
<%--                <td>1</td>--%>
<%--                <td>zhangsan</td>--%>
<%--                <td>100</td>--%>
<%--            </tr>--%>
        </tbody>
    </table>
</body>
</html>
