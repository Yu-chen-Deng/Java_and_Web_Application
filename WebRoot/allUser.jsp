<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
    <head>
        <title>用户管理 - 西南大学校园论坛</title>
        <link rel="stylesheet" href="css/admin_page.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
            <div style="display: flex; justify-content: space-between; align-items: flex-end; margin-bottom: 10px;">
                <h2 style="margin-bottom: 0px;">所有用户</h2>
                <div id="link">
                    <a href="#" onclick="showCommentModal()">添加用户</a>
                </div>
            </div>

            <c:if test="${!empty del_msg}">
                <h2 style="text-align: center; font-size: 36px">${del_msg}</h2>
            </c:if>
            <c:if test="${!empty delU_msg}">
                <h2 style="text-align: center; font-size: 36px">${delU_msg}</h2>
            </c:if>
            <c:if test="${empty userList}">
                <article>
                    <p>暂无用户。</p>
                </article>
            </c:if>
            <c:if test="${not empty userList}">
                <c:forEach var="user" items="${userList}" varStatus="i">
                    <section>
                        <article>
                            <p>用户名：${user.nickname}</p>
                            <div id="link">
                                <a href="deleteUserServlet?userId=${user.id}&URL=getAllUserServlet" target="_blank">删除用户</a>
                                <a href="findUserByIdServlet?userId=${user.id}&URL=admin_authorInfo.jsp" target="_blank">作者详情</a>
                                <a href="#" onclick="updateUserData(${user.id}); return false;" target="_blank">调试用户信息</a>
                                <a href="#" onclick="getUserData(${user.id}); return false;" target="_blank">导出为JSON文件</a>
                            </div>
                        </article>
                    </section>
                </c:forEach>

                <div class="page">
                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <a href="#?page=${i}">${i}</a>
                    </c:forEach>
                </div>
            </c:if>
        </div>

        <!-- 弹窗更新帖子框 -->
        <div id="commentModal" class="modal">
            <button onclick="hideCommentModal()" style="float: right; margin-bottom: 10px">X</button>
            <form id="userForm">
                <label for="nickname">用户名：</label>
                <input name="nickname" type="text" id="nickname" placeholder="请输入用户名，长度应在16个字符以内" required><br><br>

                <label for="username">账号</label>
                <input name="username" type="text" id="username" placeholder="请输入您的账号，长度应在6到20个字符之间" required><br><br>

                <label for="nickname">密码：</label>
                <input name="password" type="password" id="password" placeholder="请输入密码，长度应在6到20个字符之间" name="password" required><br><br>

                <input type="submit" value="添加" style="float: right; margin-top: 10px">
            </form>
        </div>

        <div id="userTable"></div>

        <script>
            function showCommentModal() {
                document.getElementById("commentModal").style.display = "block";
            }

            function hideCommentModal() {
                document.getElementById("commentModal").style.display = "none";
            }

            // 使用JavaScript发送AJAX请求到服务器接口
            function getUserData(user_Id) {
                var url = "/userToJsonServlet?userId=" + user_Id;

                fetch(url)
                    .then(response => response.json())  // 解析返回的 JSON 数据
                    .then(data => {
                        // 将 JSON 数据转化为 Blob 对象，Blob 是二进制大对象
                        const jsonBlob = new Blob([JSON.stringify(data)], { type: 'application/json' });

                        // 创建一个临时的下载链接
                        const link = document.createElement('a');
                        link.href = URL.createObjectURL(jsonBlob); // 创建 URL
                        link.download = user_Id + ".json"; // 设置下载的文件名

                        // 触发下载
                        link.click();
                    })
                    .catch(error => console.error('Error:', error));
            }

            // AJAX-渲染表格
            function updateUserData(user_Id) {
                var url = "/userToJsonServlet?userId=" + user_Id;

                // 检查表格是否已有数据
                if ($('#userTable').children().length > 0) {
                    // 如果表格已有数据，清空它
                    $('#userTable').empty();
                    return;
                }

                $.ajax({
                    url: url,
                    type: 'GET',
                    dataType: 'json',
                    success: function(data) {
                        var table = $('<table style="margin: 0px auto;"></table>');
                        $.each(data, function(key, value) {
                            var row = $('<tr></tr>');
                            row.append($('<td></td>').text(key));
                            row.append($('<td></td>').text(value));
                            table.append(row);
                        });
                        $('#userTable').append(table);
                    }
                });
            }

            // 使用jQuery获取表单数据并转换成JSON
            $('#userForm').submit(function(e) {
                e.preventDefault();
                var formData = $(this).serializeArray();
                var json = {};
                $.each(formData, function() {
                    json[this.name] = this.value;
                });

                $.ajax({
                    url: '/adminRegisterServlet',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(json),
                    success: function(response) {
                        location.reload();
                    }
                });
            });
        </script>
    </body>
</html>
