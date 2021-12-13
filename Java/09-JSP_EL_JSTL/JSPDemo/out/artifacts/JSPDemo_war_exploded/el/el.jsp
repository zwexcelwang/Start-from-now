<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL</title>
</head>
<body>
    ${3 > 4}
    <%
    request.setAttribute("name", "zhangsan");
    %>

    ${requestScope.name}

    <h2>empty运算符</h2>
    ${not empty name}
</body>
</html>
