<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--  <form action="${pageContext.request.contextPath}/user/quick22" method="post" enctype="multipart/form-data">--%>
<form action="${pageContext.request.contextPath}/user/quick23" method="post" enctype="multipart/form-data">
      名称<input type="text" name="username"><br>
      文件<input type="file" name="uploadFile"><br>
<%--      文件<input type="file" name="uploadFile2"><br>--%>
      文件<input type="file" name="uploadFile"><br>
      <input type="submit" value="提交">
  </form>
</body>
</html>
