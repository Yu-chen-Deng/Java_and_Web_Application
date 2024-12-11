<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">
    <head>
        <title>发帖 - 西南大学校园论坛</title>
        <link rel="shortcut icon" type="image/x-icon" href="swu.ico" media="screen">
        <script src="https://cdn.jsdelivr.net/npm/marked@2.1.3/marked.min.js"></script>
        <script>
            marked.setOptions({
                breaks: true,       // 支持换行符
                gfm: true,          // 支持 GitHub 风格的 Markdown（包括表格、任务列表等）
                tables: true,       // 启用表格支持
                taskLists: true     // 启用任务列表支持
            });

            function updatePreview() {
                const content = document.getElementById("content").value;
                const preview = document.getElementById("preview");

                if (typeof marked === "function") {
                    preview.innerHTML = marked(content); // 正常渲染
                } else {
                    console.error("Marked is not a function");
                }
            }
        </script>
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
                     <label for="title">标题:</label>
                     <input type="text" name="title" id="title" required><br>

                     <label for="subtitle">子标题:</label>
                     <input type="text" name="subtitle" id="subtitle" required><br>

                     <label for="content">Markdown内容:</label>
                     <textarea name="content" id="content" rows="10" cols="50" oninput="updatePreview()" required></textarea><br>

                     <label for="preview">实时预览:</label>
                     <div id="preview" style="border: 1px solid #ccc;"></div><br>

                     <input type="submit" value="提交">
                 </form>
             </article>
        </div>

    </body>
</html>
