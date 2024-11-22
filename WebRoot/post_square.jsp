<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
    <head>
        <title>帖子广场 - 西南大学校园论坛</title>
        <link rel="stylesheet" href="css/home.css">
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
                    <li><a href="index.jsp">首页</a></li>
                    <li><a href="allPostServlet?root=tourist">帖子广场</a></li>
                    <li><a href="register.jsp">注册</a></li>
                    <li><a href="login.jsp">登录</a></li>
                </ul>
            </nav>
        </header>

        <div class="container">
            <h2>帖子广场</h2>
            <c:if test="${empty postList}">
                <article>
                    <p>哎呀！抱歉，暂时无人发帖。</p>
                </article>
            </c:if>
            <c:if test="${not empty postList}">
                <c:forEach var="post" items="${postList}" varStatus="i">
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
                                <a href="upServlet">点赞(  ${post.up}  )</a>
                                <a href="postInfoServlet?fid=${post.fid}" target="_blank">评论区</a>
                                <a href="findUserByIdServlet?userId=${post.userId}&URL=authorInfo.jsp" target="_blank">作者:${post.author.nickname}</a>
                            </div>
                        </article>
                    </section>
                </c:forEach>

<%--                    分页--%>
                <div class="page">
                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <a href="allPostServlet?page=${i}">${i}</a>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </body>
</html>
