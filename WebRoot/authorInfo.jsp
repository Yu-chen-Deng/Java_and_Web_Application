<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
    <head>
        <title>用户信息 - 西南大学校园论坛</title>
        <link rel="stylesheet" href="css/home.css">
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


            <h2>${!empty userPostList ? '用户的所有帖子' : '该用户暂未发帖'}</h2>
            <c:if test="${!empty userPostList}">
                <c:forEach var="post" items="${userPostList}" varStatus="i">
                    <section>
                        <article>
                            <h3>帖子标题: ${post.title}</h3>
                            <p>${post.content}</p>
                            <fmt:formatDate value="${post.createTime}" pattern="yyyy-MM-dd HH:mm" var="formattedDate" />
                            <div id="link">
                                发布时间:${formattedDate}
                                <a href="upServlet">点赞( ${post.up  } )</a>
                                <a href="postInfoServlet?fid=${post.fid}" target="_blank">评论区</a>
                            </div>
                        </article>
                    </section>
                </c:forEach>

                <div class="page">
                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <a href="findUserByIdServlet?page=${i}&userId=${userInfo.id}&URL=authorInfo.jsp">${i}</a>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </body>
</html>
