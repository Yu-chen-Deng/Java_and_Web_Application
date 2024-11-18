<%@ page import="com.yuchen.bean.Forum" %>
<%@ page import="com.yuchen.service.ForumService" %>
<%@ page import="java.io.Writer" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">
    <head>
        <link rel="stylesheet" href="css/reply.css">
        <title>评论</title>
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
            <section>
                <article>
                    <h2>帖子标题: ${postInfo.title} ${postInfo.fid}</h2>
                    <p style="font-size: x-large">${postInfo.content}</p>
                    <fmt:formatDate value="${postInfo.createTime}" pattern="yyyy-MM-dd HH:mm" var="formattedDate" />
                    <div id="link">
                        <div style="float: right;">
                            <span style="color: #007bff; font-weight: bold;">发布时间:${formattedDate}</span>
                            <a href="upServlet?fid=${postInfo.fid}&URL=postInfoServlet">
                                ${empty up_msg  ||  upId != postInfo.fid ? '点赞  ' : '点赞成功！ '}
                                    (  ${postInfo.up}  )
                            </a>
                            <a href="findUserByIdServlet?userId=${postInfo.userId}&URL=user_authorInfo.jsp" target="_blank">
                                作者:${postInfo.author.nickname}
                            </a>
                        </div>
                    </div>
                    <div id="allReply">
                        <h4 style="text-align: center; font-size: larger">
                            所有评论  (  ${postInfo.replyCount}  )
                            <a href="#" onclick="showCommentModal()" style="font-size: medium">  ${empty reply_msg ? '发表评论' : '评论成功！再评论条？'}  </a>
                        </h4>
                        <c:choose>
                            <c:when test="${empty replyList}">
                                <h4 style="text-align: center; font-size: medium">

                                </h4>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="reply" items="${replyList}">
                                    <div class="reply">
                                        <p>回复内容：${reply.replyContent}</p>
                                        <div class="replyInfo">
                                            <a href="findUserByIdServlet?userId=${reply.author.id}&URL=user_authorInfo.jsp" target="_blank">
                                                回复人:${reply.author.nickname}
                                            </a>
<%--                                            <span>回复人：${reply.author.nickname}</span>--%>
                                            <fmt:formatDate value="${reply.replyTime}" pattern="yyyy-MM-dd HH:mm" var="formattedDate" />
                                            <span>回复时间：${formattedDate}</span>
                                            <a href="replyUpServlet?replyId=${reply.replyId}&fid=${postInfo.fid}">
                                                    ${empty up_msg  || upId != reply.replyId ? '点赞  ' : '点赞成功！ '}(  ${reply.up}  )
                                            </a>

                                        </div>
                                    </div>
                                </c:forEach>
                                <div class="page">
                                    <c:forEach var="i" begin="1" end="${totalPages}">
                                        <a href="getReplyByFidServlet?URL=reply.jsp&page=${i}&fid=${postInfo.fid}">${i}</a>
                                    </c:forEach>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </article>
            </section>
        </div>


        <div id="commentModal" class="modal">
            <button onclick="hideCommentModal()" style="float: right; margin-bottom: 10px">X</button>
            <form name="reply_form" action="replyServlet?fid=${postInfo.fid}" method="post">
                <textarea id="replyContent" name="reply_content" required></textarea>
                <input type="submit" value="发送评论" style="float: right; margin-top: 10px">
            </form>
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
