<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
    <head>
        <title>管理 - 西南大学校园论坛</title>
        <link rel="stylesheet" href="css/admin_page.css">
    </head>
    <body>
        <header>
            <h1>西南大学 校园论坛</h1>
            <nav>
                <ul>
                    <li><a href="indexServlet">帖子列表</a></li>
                    <li><a href="getAllUserServlet">用户管理</a></li>
                    <li><a href="logoutServlet">登出</a></li>
                </ul>
            </nav>
            <a href="#">${user.nickname} (管理员)</a>
        </header>

        <div class="container">
            <h2>所有帖子</h2>
            <c:if test="${!empty set_msg}">
                <h2>${set_msg}</h2>
            </c:if>
            <c:if test="${!empty del_msg}">
                <h2 style="text-align: center; font-size: 36px">${del_msg}</h2>
            </c:if>
            <c:if test="${empty postList}">
                <article>
                    <p>暂时无人发帖。</p>
                </article>
            </c:if>
            <c:if test="${not empty postList}">
                <c:forEach var="post" items="${postList}" varStatus="i">
                    <section>
                        <article>
                            <h3>帖子标题: ${post.title}</h3>
                            <p>${post.subtitle}</p>
                            <fmt:formatDate value="${post.createTime}" pattern="yyyy-MM-dd HH:mm" var="formattedDate" />
                            <div id="link">
                                发布时间:${formattedDate}
                                <c:choose>
                                    <c:when test="${post.selected == 1}">
                                        <a href="unsetSelectedPostServlet?fid=${post.fid}"> 取消精选帖 </a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="setSelectedPostServlet?fid=${post.fid}">设置为精选帖</a>
                                    </c:otherwise>
                                </c:choose>
                                <a href="adminDeletePostServlet?fid=${post.fid}&URL=admin_page.jsp">删除帖子</a>
                                <a href="findUserByIdServlet?userId=${post.userId}&URL=admin_authorInfo.jsp" target="_blank">作者:${post.author.nickname}</a>
                            </div>
                        </article>
                    </section>
                </c:forEach>

                <div class="page">
                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <a href="allPostServlet?page=${i}">${i}</a>
                    </c:forEach>
                </div>
            </c:if>
        </div>

    </body>
</html>
