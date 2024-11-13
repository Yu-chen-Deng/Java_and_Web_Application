<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
    <head>
        <title>用户页面</title>
        <link rel="stylesheet" href="css/user_page.css">
    </head>
    <body>
        <nav>
            <ul>
                <li>${user.nickname}, 欢迎您访问 xxx 校园论坛。</li>
                <li><a href="indexServlet">首页</a> </li>
                <li><a href="post.jsp" target="_blank">发帖</a> </li>
                <li><a href="allPostServlet">帖子广场</a></li>
                <li><a href="getUserPostListServlet">我的帖子</a></li>
                <li><a href="user_info.jsp">个人信息</a></li>
                <li><a href="logoutServlet">登出</a></li>
            </ul>
        </nav>

        <div class="container">
            <section class="selected-post">
                <h2>精选帖子</h2>
                <c:if test="${empty selectedPostList}">
                    <article>
                        <p>哎呀！抱歉，暂时没有精选帖。</p>
                    </article>
                </c:if>
                <c:if test="${not empty selectedPostList}">
                    <c:forEach var="post" items="${selectedPostList}" varStatus="i">
                        <c:if test="${i.index < 2}">
                            <article>
                                <h3>帖子标题: ${post.title}</h3>
                                <p>${post.content}</p>
                                <fmt:formatDate value="${post.createTime}" pattern="yyyy-MM-dd HH:mm" var="formattedDate" />
                                <div id="link">
                                    发布时间:${formattedDate}
                                    <a href="upServlet?fid=${post.fid}&URL=indexServlet">${empty up_msg  ||  upId != post.fid ? '点赞  ' : '点赞成功！ '}(  ${post.up}  )</a>
                                    <a href="getReplyByFidServlet?fid=${post.fid}&URL=reply.jsp" target="_blank">评论区</a>
                                    <a href="findUserByIdServlet?userId=${post.userId}&URL=user_authorInfo.jsp" target="_blank">作者:${post.author.nickname}</a>
                                </div>
                            </article>
                        </c:if>
                    </c:forEach>
                </c:if>
            </section>

            <section class="recent-posts">
                <h2>最新发布</h2>
                <c:if test="${empty recentPostList}">
                    <article>
                        <p>哎呀！抱歉，暂时无人发帖.</p>
                    </article>
                </c:if>

                <c:if test="${not empty recentPostList}">
                    <c:forEach var="post" items="${recentPostList}" varStatus="i">
                        <c:if test="${i.index < 5}">
                            <article>
                                <h3>帖子标题: ${post.title}</h3>
                                <fmt:formatDate value="${post.createTime}" pattern="yyyy-MM-dd HH:mm" var="formattedDate" />
                                <div id="link">
                                    发布时间:${formattedDate}
                                    <a href="upServlet?fid=${post.fid}&URL=indexServlet">${empty up_msg  ||  upId != post.fid ? '点赞  ' : '点赞成功！ '}(  ${post.up}  )</a>
                                    <a href="findUserByIdServlet?userId=${post.userId}&URL=user_authorInfo.jsp" target="_blank">作者:${post.author.nickname}</a>
                                    <a href="getReplyByFidServlet?fid=${post.fid}&URL=reply.jsp" target="_blank">点此查看全帖</a>
                                </div>
                            </article>
                        </c:if>
                    </c:forEach>
                </c:if>
            </section>
        </div>
    </body>
</html>
