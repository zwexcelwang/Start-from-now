
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        window.onload = function (){
            document.getElementById("check_code_img").onclick = function () {
                this.src = "/Cookie_Session/checkCodeServlet?time=" + new Date().getTime();
            }
        }
    </script>
    <style>
        /*div {*/
        /*    color：red;*/
        /*}*/
    </style>
</head>
<body>
    <form action="/Cookie_Session/loginServlet" method="post">
        <table>
            <tr>
                <td>用户名</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>验证码</td>
                <td><input type="text" name="checkCode"></td>
            </tr>
            <tr>
                <td colspan="2"><img id="check_code_img" src="/Cookie_Session/checkCodeServlet"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="登录"></td>
            </tr>
        </table>
    </form>

    <div>
        <%=request.getAttribute("CheckCodeError") == null ?
                "": request.getAttribute("CheckCodeError")%>
    </div>
    <div>
        <%=request.getAttribute("LoginError") == null ?
                "": request.getAttribute("LoginError")%>
    </div>
</body>
</html>
