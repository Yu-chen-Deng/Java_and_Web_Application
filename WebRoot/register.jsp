<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <link rel="stylesheet" href="css/register.css">
    <title>欢迎注册</title>
</head>
<body>
    <div class="register-container">
        <h2>${empty register_msg ? '欢迎注册' : register_msg}</h2>
        <form class="register-form" action="registerServlet" method="post">
            <div class="form-group">
                <label for="nickname">用户名</label>
                <input name="nickname" type="text" id="nickname" placeholder="请输入您的用户名，长度应在16个字符以内" style="width: 350px;" required>
            </div>
            <div class="form-group">
                <label for="username">账号</label>
                <input name="username" type="text" id="username" placeholder="请输入您的账号，长度应在6到20个字符之间" style="width: 350px;" required>
            </div>
            <div class="form-group">
                <label for="password">密码</label>
                <input name="password" type="password" id="password" placeholder="请输入您的密码，长度应在6到20个字符之间" style="width: 350px;" required>
            </div>
            <button type="submit">注册</button>
        </form>
        <div class="links">
            <p>已有账号?<a href="login.jsp">登录</a> | <a href="indexServlet">返回主页</a> </p>
        </div>
    </div>
</body>
</html>
