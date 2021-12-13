<%@ page import="zui.domain.User" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        User user = new User();
        user.setAge(2);
        user.setName("zhangsan");
        user.setBirthday(new Date());

        request.setAttribute("user", user);

        List list = new ArrayList();
        list.add("aaa");
        list.add("bbb");
        list.add(user);
        request.setAttribute("list",list);

        Map map = new HashMap();
        map.put("name", "name");
        map.put("gender", "male");
        map.put("user", user);
        request.setAttribute("map", map);
    %>

    <h1>el获取对象中的值</h1>
    ${requestScope.user.name}<br>
    ${user.age}<br>
    ${user.birthday}<br>
    ${user.birthdayStr}<br>

    <h1>el获取list中的值</h1>
    ${requestScope.list}<br>
    ${requestScope.list[0]}<br>
    ${requestScope.list[1]}<br>
    ${requestScope.list[2].name}<br>

    ${requestScope.list[10]}<br>

    <h1>el获取Map中的值</h1>
    ${requestScope.map.name}<br>
    ${requestScope.map.gender}<br>
    ${requestScope.map["gender"]}<br>
    ${requestScope.map.user.name}<br>

</body>
</html>
