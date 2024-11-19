<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">
    <head>
        <title>发帖 - 西南大学校园论坛</title>
        <link rel="stylesheet" href="css/post.css">
    </head>
    <body>
        <header>
            <h1>西南大学 校园论坛</h1>
            <nav>
                <ul>
                    <li><a href="indexServlet">首页</a> </li>
                    <li><a href="post.jsp" target="_blank">发帖</a> </li>
                    <li><a href="allPostServlet">帖子广场</a></li>
                    <li><a href="getUserPostListServlet">我的帖子</a></li>
                    <li><a href="user_info.jsp">个人信息</a></li>
                    <li><a href="logoutServlet">登出</a></li>
                </ul>
            </nav>
            <a href="user_info.jsp">${user.nickname}</a>
        </header>

        <div style="width: 80%;margin: 20px auto;overflow: hidden;">
             <article>
                 <h2>发帖</h2>
                 <form id="post_form" action="postServlet" method="post">
                     <p>标题</p>
                     <input name="title" type="text" rows="3" placeholder="最多20个字" required>
                     <p>内容<textarea name="content" rows="10" placeholder="可垂直方向拉伸文本框" required></textarea></p>
                     <input type="submit" value="发布">
                 </form>
             </article>
        </div>

    </body>
</html>
