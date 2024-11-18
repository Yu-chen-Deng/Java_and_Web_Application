<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
    <head>
        <title>帖子详情</title>
        <link rel="stylesheet" href="css/postInfo.css">
    </head>
    <body>
        <header>
            <h1>西南大学 校园论坛</h1>
            <nav>
                <ul>
                    <li><a href="indexServlet">首页</a></li>
                    <li><a href="allPostServlet?root=tourist">帖子广场</a></li>
                    <li><a href="register.jsp">注册</a></li>
                    <li><a href="login.jsp">登录</a></li>
                </ul>
            </nav>
        </header>

        <div class="container">
            <section>
                <article>
                    <h2>帖子标题: ${postInfo.title}</h2>
                    <p style="font-size: x-large">${postInfo.content}</p>
                    <fmt:formatDate value="${postInfo.createTime}" pattern="yyyy-MM-dd HH:mm" var="formattedDate" />
                    <div id="link">
                        <div style="float: right;">
                            <span style="color: #007bff; font-weight: bold;">发布时间:${formattedDate}</span>
                            <a href="upServlet">点赞( ${postInfo.up} )</a>
                            <a href="findUserByIdServlet?userId=${postInfo.userId}&URL=authorInfo.jsp" target="_blank">作者:${postInfo.author.nickname}</a>
                        </div>
                    </div>

                    <div id="allReply">
                        <h4>所有评论   <a href="login.jsp"> 您尚未登录，点我登录后方可发送评论~  </a></h4>
                        <c:choose>
                            <c:when test="${empty replyList}">
                                暂时无人回复
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="reply" items="${replyList}" varStatus="i">
                                    <div class="reply">
                                        <p>回复内容：${reply.replyContent}</p>
                                        <div class="replyInfo">
                                            <a href="findUserByIdServlet?userId=${reply.author.id}&URL=authorInfo.jsp" target="_blank">
                                                回复人:${reply.author.nickname}
                                            </a>
<%--                                            <span>回复人：${reply.author.nickname}</span>--%>
                                            <fmt:formatDate value="${reply.replyTime}" pattern="yyyy-MM-dd HH:mm" var="formattedDate" />
                                            <span>回复时间：${formattedDate}</span>
                                            <a href="replyUpServlet">点赞 (  ${reply.up}  )</a>
                                        </div>
                                    </div>
                                </c:forEach>

                                <div class="page">
                                    <c:forEach var="i" begin="1" end="${totalPages}">
                                        <a href="postInfoServlet?page=${i}&fid=${postInfo.fid}">${i}</a>
                                    </c:forEach>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </article>
            </section>
        </div>

    </body>
</html>
