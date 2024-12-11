<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
    <head>
        <title>用户信息 - 西南大学校园论坛</title>
        <link rel="shortcut icon" type="image/x-icon" href="swu.ico" media="screen">
        <link rel="stylesheet" href="css/user_authorInfo.css">
        <style>
            h2 {
                font-size: 36px;
                text-align: center;
                color: #333;
            }
            #info {
                background-color: #f6f6f6;
                padding: 20px;
                margin-bottom: 30px;
            }

            #info p {
                margin-bottom: 15px;
                color: #333;
                font-size: 18px;
                font-weight: bold;
            }

            #info article {
                padding: 15px;
                margin-bottom: 20px;
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
            <h2>用户信息</h2>
            <div id="info">
                <section>
                    <article>
                        <p>用户名：${userInfo.nickname}</p>
                        <p>账号：${userInfo.username}</p>
                        <p>生日：
                            <c:choose>
                                <c:when test="${empty userInfo.birthday}">
                                    该用户暂时未填写生日
                                </c:when>
                                <c:otherwise>
                                    <fmt:formatDate value="${userInfo.birthday}" pattern="yyyy-MM-dd" />
                                </c:otherwise>
                            </c:choose>
                        </p>
                        <p>性别: ${empty userInfo.sex ? '该用户暂时未填写性别' : userInfo.sex}</p>
                        <p>电话: ${empty userInfo.phone ? '该用户暂时未填写电话' : userInfo.phone}</p>
                        <p>邮箱：${empty userInfo.email ? '该用户暂时未填写邮箱' : userInfo.email}</p>
                        <p>个性签名：${empty userInfo.signature ? '该用户暂时未填写个性签名' : userInfo.signature}</p>
                    </article>
                </section>
            </div>

            <h2>${!empty userPostList ? '该用户的所有帖子' : '该用户暂未发帖'}</h2>
            <c:if test="${!empty userPostList}">
                <c:forEach var="post" items="${userPostList}" varStatus="i">
                    <section>
                        <article>
                            <h3>帖子标题: ${post.title}</h3>
                            <p id='preview'></p>
                            <script>
                                // 示例：从后台传来的内容
                                const content = `${post.subtitle}`
                                // 提取出标题和普通文本部分
                                const extractedContent = extractHeadersAndText(content);
                                // 将提取的标题和普通文本部分显示在页面上
                                document.getElementById('preview').innerText = extractedContent;
                            </script>
                            <fmt:formatDate value="${post.createTime}" pattern="yyyy-MM-dd HH:mm" var="formattedDate" />
                            <div id="link">
                                发布时间:${formattedDate}
                                <a href="upServlet?fid=${post.fid}&URL=getPostListByIdServlet&id=${userInfo.id}">
                                        ${empty up_msg  ||  upId != post.fid ? '点赞  ' : '点赞成功！ '}(  ${post.up}  )
                                </a>
                                <a href="getReplyByFidServlet?fid=${post.fid}&URL=reply.jsp" target="_blank">评论区</a>
                            </div>
                        </article>
                    </section>
                </c:forEach>

                <div class="page">
                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <a href="findUserByIdServlet?page=${i}&userId=${userInfo.id}&URL=user_authorInfo.jsp">${i}</a>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </body>
</html>