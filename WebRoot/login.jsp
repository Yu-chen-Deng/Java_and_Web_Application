<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">
    <head>
        <title>欢迎登陆</title>
        <link rel="stylesheet" href="css/login.css">
    </head>
    <body>
        <div class="login-container">
            <c:choose>
                <c:when test="${!empty login_msg}">
                    <h2>${login_msg}</h2>
                </c:when>
                <c:otherwise>
                    <h2>${empty register_msg ? '欢迎登陆' : register_msg}</h2>
                </c:otherwise>
            </c:choose>

            <form class="login-form" method="post" action="loginServlet">
                <div class="form-group">
                    <label for="username">账号</label>
                    <input type="text" id="username" name="username" placeholder="请输入用户名" value="${cookie.username.value}" required >
                </div>
                <div class="form-group">
                    <label for="password">密码</label>
                    <input type="password" id="password" name="password" placeholder="请输入密码" value="${cookie.password.value}" required>
                </div>
                <div class="form-group" style="display: flex;align-items: center;">
                    <input type="checkbox" id="remember" name="remember" value="1" style="margin-right: 15px;">
                    <label for="remember">记住账号</label>
                </div>
                <div class="login-options">
                    <input type="radio" id="radio-1" name="type" value="1" checked>
                    <label for="radio-1">普通用户</label>
                    <input type="radio" id="radio-2" name="type" value="2">
                    <label for="radio-2">管理员</label>
                </div>
                <button type="submit">登录</button>
            </form>

            <div class="links">
                <p><a href="indexServlet">返回主页</a> | <a href="register.jsp">注册新账号</a></p>
            </div>
        </div>
    </body>
</html>
