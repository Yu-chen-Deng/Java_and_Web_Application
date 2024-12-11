<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
    <head>
        <title>用户信息 - 西南大学校园论坛</title>
        <link rel="shortcut icon" type="image/x-icon" href="swu.ico" media="screen">
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
            <c:choose>
                <c:when test="${empty delU_msg}">
                    <h2>
                        用户信息
                        <a href="deleteUserServlet?userId=${userInfo.id}&URL=findUserByIdServlet" style="font-size: medium; float: right">删除用户</a>
                    </h2>
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

                    <c:if test="${!empty del_msg}">
                        <h2>${del_msg}</h2>
                    </c:if>
                    <h2>${!empty userPostList ? '该用户的所有帖子' : '该用户暂未发帖'}</h2>
                    <c:if test="${!empty userPostList}">
                        <c:forEach var="post" items="${userPostList}" varStatus="i">
                            <section>
                                <article>
                                    <h3>帖子标题: ${post.title}</h3>
                                    <p>${post.subtitle}</p>
                                    <fmt:formatDate value="${post.createTime}" pattern="yyyy-MM-dd HH:mm" var="formattedDate" />
                                    <div id="link">
                                        发布时间:${formattedDate}
                                        <a href="adminDeletePostServlet?fid=${post.fid}&URL=findUserByIdServlet&userId=${post.userId}">删除帖子</a>
                                    </div>
                                </article>
                            </section>
                        </c:forEach>

                        <div class="page">
                            <c:forEach var="i" begin="1" end="${totalPages}">
                                <a href="findUserByIdServlet?page=${i}&userId=${userInfo.id}&URL=admin_authorInfo.jsp">${i}</a>
                            </c:forEach>
                        </div>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <h2>${delU_msg}</h2>
                </c:otherwise>
            </c:choose>




        </div>
    </body>
</html>
