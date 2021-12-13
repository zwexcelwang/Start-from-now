<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:if test="true">
        <h1>True</h1>
    </c:if>

    <%
        int number = 4;
        List list = new ArrayList();
        list.add("hhh");
        list.add("hhhh");
        request.setAttribute("list", list);
        request.setAttribute("number", number);
    %>

    <c:if test="${not empty list}">
        遍历
    </c:if>

    <c:if test="${number%2 == 0}">
        ${number}为偶数
    </c:if>

</body>
</html>
