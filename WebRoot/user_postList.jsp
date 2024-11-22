<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
    <head>
        <title>我的帖子 - 西南大学校园论坛</title>
        <link rel="stylesheet" href="css/reply.css">
        <style>
            input[type="text"] {
                width: calc(100% - 20px);
                padding: 10px;
                border-radius: 20px;
                margin-bottom: 5px;
            }
        </style>
        <script>
            // 处理多行文本，筛选出以 #、##、### 开头的标题和没有符号的普通文本
            function extractHeadersAndText(content) {
                // 按行分割文本
                const lines = content.split('\n');

                // 正则匹配：筛选出标题行（以 # 开头）和没有符号的普通文本行
                const resultLines = lines.filter(
                line => /^#{1,6}\s/.test(line) || /^[^\#\*\|\[\-\+].*/.test(line)).map(
                line => {// 对于标题行，去掉开头的 # 和空格
                    if(/^#{1,6}\s/.test(line)) {
                        // 去掉 # 和后面的空格
                        return line.replace(/^#{1,6}\s/, '');}
                    return line;  // 普通文本行保持不变
                    });
                // 将符合条件的行重新拼接成新的字符串
                return resultLines.join(' ');
            }
        </script>
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

        <div class="container">
            <h2 style="text-align: center; font-size: 36px">我的帖子</h2>
            <c:if test="${!empty post_msg}">
                <h2 style="text-align: center; font-size: 36px">${post_msg}</h2>
            </c:if>
            <c:if test="${!empty del_msg}">
                <h2 style="text-align: center; font-size: 36px">${del_msg}</h2>
            </c:if>
            <c:if test="${!empty update_msg}">
                <h2 style="text-align: center; font-size: 36px">${update_msg}</h2>
            </c:if>
            <c:if test="${empty userPostList}">
                <article>
                    <p>哎呀！你暂未发帖。</p>
                </article>
            </c:if>
            <c:if test="${!empty userPostList}">
                <c:forEach var="post" items="${userPostList}" varStatus="i">
                    <section>
                        <article>
                            <h3>帖子标题: ${post.title}</h3>
                            <p id='preview'></p>
                            <script>
                                // 示例：从后台传来的内容
                                const content = `${post.content}`
                                // 提取出标题和普通文本部分
                                const extractedContent = extractHeadersAndText(content);
                                // 将提取的标题和普通文本部分显示在页面上
                                document.getElementById('preview').innerText = extractedContent;
                            </script>
                            <fmt:formatDate value="${post.createTime}" pattern="yyyy-MM-dd HH:mm" var="formattedDate" />
                            <div id="link">
                                <span style="color: #037BC2; font-weight: bold;">发布时间:${formattedDate}</span>
                                <a href="getReplyByFidServlet?fid=${post.fid}&URL=reply.jsp" target="_blank">评论区</a>
                                <a href="#" onclick="showCommentModal()" style="font-size: medium">更新帖子</a>
                                <a href="deletePostServlet?fid=${post.fid}">删除帖子</a>
                            </div>
                        </article>
                    </section>

                    <!-- 弹窗更新帖子框 -->
                    <div id="commentModal" class="modal">
                        <button onclick="hideCommentModal()" style="float: right; margin-bottom: 10px">X</button>
                        <form action="updatePostServlet?fid=${post.fid}" method="post">
                            <label for="title">标题：</label>
                            <input type="text" name="title" id="title" value="${post.title}" required><br><br>

                            <label for="content">更新内容：</label><br>
                            <textarea id="content" name="content" required style="margin-top: 15px;">${post.content}</textarea><br><br>

                            <input type="submit" value="提交" style="float: right; margin-top: 10px">
                        </form>
                    </div>
                </c:forEach>

                <!-- 分页控件 -->
                <div class="page">
                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <a href="getUserPostListServlet?page=${i}">${i}</a>
                    </c:forEach>
                </div>


            </c:if>
        </div>


        <!-- JavaScript部分 -->
        <script>
            function showCommentModal() {
                document.getElementById("commentModal").style.display = "block";
            }

            function hideCommentModal() {
                document.getElementById("commentModal").style.display = "none";
            }
        </script>
    </body>
</html>
