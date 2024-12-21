<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">
    <head>
        <title>初始化 - 西南大学校园论坛</title>
        <link rel="shortcut icon" type="image/x-icon" href="swu.ico" media="screen">
        <link rel="stylesheet" href="css/initDB.css">
    </head>
    <body>
        <div class="init-container">
            <h2 style="margin-bottom:10px;">${empty init_msg ? '欢迎使用 西南大学校园论坛 系统' : init_msg}</h2>
            <p>请注册管理员账号，完成系统的初始化</p><br>

            <form class="init-form" method="post" action="initDBServlet">
                <div class="form-group">
                    <label for="nickname">管理员用户名</label>
                    <input name="nickname" type="text" id="nickname" placeholder="请输入您的用户名，长度应在16个字符以内" required>
                </div>
                <div class="form-group">
                    <label for="username">管理员账号</label>
                    <input name="username" type="text" id="username" placeholder="请输入您的管理员账号，长度应在6到20个字符之间" required>
                </div>
                <div class="form-group">
                    <label for="password">管理员密码</label>
                    <input name="password" type="password" id="password" placeholder="请输入您的管理员密码，长度应在6到20个字符之间" required>
                </div>

                <button type="submit">初始化</button>
            </form>
        </div>
    </body>
</html>
