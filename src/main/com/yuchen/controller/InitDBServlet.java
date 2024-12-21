package com.yuchen.controller;

import com.yuchen.bean.User;
import com.yuchen.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;

@WebServlet("/initDBServlet")
public class InitDBServlet extends HttpServlet {

    // 数据库连接配置
    private static final String DB_URL = "jdbc:mysql://localhost:3306";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nickname = req.getParameter("nickname");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //检查用户名长度
        if (nickname.length() > 16) {
            req.setAttribute("init_msg", "用户名过长");
            req.getRequestDispatcher("/initDB.jsp").forward(req, resp);
            return ;
        }
        //检查账号长度
        if (username.length() < 6 || username.length() > 20 ){
            req.setAttribute("init_msg", "账号格式错误");
            req.getRequestDispatcher("/initDB.jsp").forward(req, resp);

            return ;
        }
        //检查密码长度
        if (password.length() < 6 || password.length() > 20) {
            req.setAttribute("init_msg", "密码格式错误");
            req.getRequestDispatcher("/initDB.jsp").forward(req, resp);
            return;
        }


        // 执行数据库初始化
        try {
            // 1. 创建数据库连接
            Class.forName("com.mysql.cj.jdbc.Driver"); // 手动加载驱动
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 2. 创建数据库
            String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS swubbs";
            Statement statement = connection.createStatement();
            statement.executeUpdate(createDatabaseSQL);

            // 3. 使用新创建的数据库
            String useDatabaseSQL = "USE swubbs";
            statement.executeUpdate(useDatabaseSQL);

            // 4. 创建表（adminuser_info）
            String createAdminUserTableSQL = "CREATE TABLE IF NOT EXISTS adminuser_info ("
                    + "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                    + "nickname VARCHAR(20) NOT NULL, "
                    + "username VARCHAR(20) NOT NULL, "
                    + "password VARCHAR(20) NOT NULL"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;";

            statement.executeUpdate(createAdminUserTableSQL);

            // 5. 创建表（forum_info）
            String createForumTableSQL = "CREATE TABLE IF NOT EXISTS forum_info ("
                    + "fid INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                    + "title VARCHAR(255) NOT NULL, "
                    + "subtitle VARCHAR(255) DEFAULT NULL, "
                    + "content TEXT, "
                    + "create_time DATETIME NOT NULL, "
                    + "up INT DEFAULT 0, "
                    + "reply_count INT DEFAULT 0, "
                    + "user_id INT NOT NULL, "
                    + "selected INT DEFAULT NULL"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;";

            statement.executeUpdate(createForumTableSQL);

            // 6. 创建表（reply_info）
            String createReplyTableSQL = "CREATE TABLE IF NOT EXISTS reply_info ("
                    + "reply_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                    + "reply_content TEXT NOT NULL, "
                    + "reply_time DATETIME NOT NULL, "
                    + "up INT DEFAULT 0, "
                    + "user_id INT NOT NULL, "
                    + "fid INT NOT NULL"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;";

            statement.executeUpdate(createReplyTableSQL);

            // 7. 创建表（user_info）
            String createUserTableSQL = "CREATE TABLE IF NOT EXISTS user_info ("
                    + "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                    + "nickname VARCHAR(20) NOT NULL, "
                    + "username VARCHAR(20) NOT NULL, "
                    + "password VARCHAR(20) NOT NULL, "
                    + "birthday DATE DEFAULT NULL, "
                    + "sex VARCHAR(2) DEFAULT NULL, "
                    + "phone VARCHAR(20) DEFAULT NULL, "
                    + "email VARCHAR(20) DEFAULT NULL, "
                    + "signature VARCHAR(255) DEFAULT NULL, "
                    + "avatar VARCHAR(255) NOT NULL"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;";

            statement.executeUpdate(createUserTableSQL);

            // 8. 插入管理员信息
            String insertAdminUserSQL = "INSERT INTO adminuser_info (nickname, username, password) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertAdminUserSQL);
            preparedStatement.setString(1, nickname);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();

            // 9. 重定向到首页
            req.getRequestDispatcher("/indexServlet").forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("init_msg", "数据库初始化失败: " + e.getMessage());
            req.getRequestDispatcher("/initDB.jsp").forward(req, resp);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            req.setAttribute("init_msg", "数据库初始化失败: " + e.getMessage());
            req.getRequestDispatcher("/initDB.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
