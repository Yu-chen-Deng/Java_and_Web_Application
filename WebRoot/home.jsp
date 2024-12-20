<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">
    <head>
        <link rel="stylesheet" href="css/home.css">
        <link rel="shortcut icon" type="image/x-icon" href="swu.ico" media="screen">
        <title>首页 - 西南大学校园论坛</title>
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
            <img src="./img/yl.png" class="decoration"></img>
        </header>

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
                                    <p>${post.subtitle}</p>
                                    <fmt:formatDate value="${post.createTime}" pattern="yyyy-MM-dd HH:mm" var="formattedDate" />
                                    <div id="link">
                                        <span style="color: #037BC2; font-weight: bold;">发布时间:${formattedDate}</span>
                                        <a href="upServlet">点赞( ${post.up} )</a>
                                        <a href="postInfoServlet?fid=${post.fid}" target="_blank">评论区</a>
                                        <a href="findUserByIdServlet?userId=${post.userId}&URL=authorInfo.jsp" target="_blank">作者:${post.author.nickname}</a>
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
                        <p>哎呀！抱歉，暂时无人发帖。</p>
                    </article>
                </c:if>

                <c:if test="${not empty recentPostList}">
                    <c:forEach var="post" items="${recentPostList}" varStatus="i">
                        <c:if test="${i.index < 5}">
                            <article>
                                <h3>帖子标题: ${post.title}</h3>
                                <fmt:formatDate value="${post.createTime}" pattern="yyyy-MM-dd HH:mm" var="formattedDate" />
                                <div id="link">
                                    <span style="color: #037BC2; font-weight: bold;">发布时间:${formattedDate}</span>
                                    <a href="upServlet">点赞( ${post.up} )</a>
                                    <a href="findUserByIdServlet?userId=${post.userId}&URL=authorInfo.jsp" target="_blank">作者:${post.author.nickname}</a>
                                    <a href="postInfoServlet?fid=${post.fid}" target="_blank">点此查看全帖</a>
                                </div>
                            </article>
                        </c:if>
                    </c:forEach>
                </c:if>
            </section>
        </div>
    </body>
</html>
