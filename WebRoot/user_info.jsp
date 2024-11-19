<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
    <head>
        <title>个人信息 - 西南大学校园论坛</title>
        <link rel="stylesheet" href="css/user_page.css">
        <style>
            h2 {
                text-align: center;
            }
            .container a {
                margin-right: 25px;
                float: right;
                text-decoration: none;
                color: #007bff;
                transition: color 0.3s ease;
            }
            .container p {
                margin-left: 25px;
                font-size: 18px;
            }
            .container a:hover {
                color: #0056b3;
            }

            .container form {
                margin-top: 10px;
            }

            .container input[type="text"],
            .container input[type="password"],
            .container input[type="date"],
            .container input[type="tel"],
            .container textarea {
                width: 100%;
                padding: 8px;
                margin-bottom: 10px;
                border-radius: 5px;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }

            .container input[type="submit"] {
                padding: 8px 16px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .container input[type="submit"]:hover {
                background-color: #0056b3;
            }
        </style>
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
                <h2>我的信息</h2>
                <article>
                    <p>用户名：${empty user.nickname ? '您暂未填写名字' : user.nickname}
                        <a href="#" onclick="displayForm('nickname')">
                            ${empty update_msg || t != "nickname" ? '点此修改用户名' : (update_msg != "更新成功" ? update_msg :'您已成功修改用户名')}
                        </a>
                    </p>
                    <form id="nickname" style="display: none" action="upDateUserInfoServlet" method="post">
                        <input type="hidden" name="t" value="nickname">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="text" placeholder="请输入您的用户名，长度应在16个字符以内" name="nickname" required>
                        <input type="submit" value="提交">
                    </form>

                    <p>账号：${user.username}</p>

                    <p>密码：${user.password}
                        <a href="#" onclick="displayForm('password')">
                            ${empty update_msg || t != "password" ? '点此修改密码' : (update_msg != "更新成功" ? update_msg : '您已成功修改密码')}
                        </a>
                    </p>
                    <form id="password" style="display: none" action="upDateUserInfoServlet" method="post">
                        <input type="hidden" name="t" value="password">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="password" placeholder="请输入您的密码，长度应在6到20个字符之间" name="password" required>
                        <input type="submit" value="提交">
                    </form>

                    <p>生日：
                        <c:choose>
                            <c:when test="${empty user.birthday}">
                                您暂时未填写生日
                            </c:when>
                            <c:otherwise>
                                <fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd" />
                            </c:otherwise>
                        </c:choose>
                        <a href="#" onclick="displayForm('birthday')">
                            ${empty update_msg || !t.equals("birthday") ? '点此修改生日' : '您已成功修改生日'}
                        </a>
                    </p>
                    <form id="birthday" style="display: none" action="upDateUserInfoServlet" method="post">
                        <input type="hidden" name="t" value="birthday">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="date" name="birthday" required>
                        <input type="submit" value="提交">
                    </form>

                    <p>性别: ${empty user.sex ? '您暂时未填写性别' : user.sex}
                        <a href="#" onclick="displayForm('sex')">
                            ${empty update_msg || t !=  "sex" ? '点此修改性别' : '您已成功修改性别'}
                        </a>
                    </p>
                    <form id="sex" style="display: none" action="upDateUserInfoServlet" method="post">
                        <input type="hidden" name="t" value="sex">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="radio" id="male" name="sex" value="男">
                        <label for="male">男</label>
                        <input type="radio" id="female" name="sex" value="女">
                        <label for="female">女</label>
                        <input type="submit" value="提交">
                    </form>

                    <p>电话: ${empty user.phone ? '您暂时未填写电话' : user.phone}
                        <a href="#" onclick="displayForm('phone')">
                            ${empty update_msg || t !=  "phone" ? '点此修改电话' : '您已成功修改电话'}
                        </a>
                    </p>
                    <form id="phone" style="display: none" action="upDateUserInfoServlet" method="post">
                        <input type="hidden" name="t" value="phone">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="tel" name="phone" placeholder="请您输入的电话号码">
                        <input type="submit" value="提交">
                    </form>

                    <p>邮箱：${empty user.email ? '您暂时未填写邮箱' : user.email}
                        <a href="#" onclick="displayForm('email')">
                            ${empty update_msg || t !=  "email" ? '点此修改邮箱' : '您已成功修改邮箱'}
                        </a>
                    </p>
                    <form id="email" style="display: none" action="upDateUserInfoServlet" method="post">
                        <input type="hidden" name="t" value="email">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="text" name="email" placeholder="请输入您的邮箱地址">
                        <input type="submit" value="提交">
                    </form>

                    <p>个性签名：${empty user.signature ? '您暂时未填写签名' : user.signature}
                        <a href="#" onclick="displayForm('signature')">
                            ${empty update_msg || t != "signature" ? '点此修改个性签名' : '您已成功修改个性签名'}
                        </a>
                    </p>
                    <form id="signature" style="display: none" action="upDateUserInfoServlet" method="post">
                        <input type="hidden" name="t" value="signature">
                        <input type="hidden" name="id" value="${user.id}">
                        <textarea name="signature" placeholder="请输入您的个人简介"></textarea>
                        <input type="submit" value="提交">
                    </form>
                </article>
            </section>
        </div>

        <script>
            function displayForm(formId) {
                document.getElementById(formId).style.display = "block";
            }
        </script>
    </body>
</html>
