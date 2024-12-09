package com.yuchen.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebFilter(urlPatterns = "/indexServlet")
public class initDBFilter implements jakarta.servlet.Filter {

    private static boolean isDatabaseConnected = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 在 Filter 初始化时检查数据库连接
        checkDatabaseConnection();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 如果数据库未连接，重定向到数据库初始化页面
        if (!isDatabaseConnected) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("/initDB.jsp");  // 跳转到数据库初始化页面
            return;
        }

        // 如果数据库连接正常，继续处理请求
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 清理资源
    }

    private void checkDatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 手动加载驱动
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/swubbs?useSSL=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
                    "root",
                    "123456");
            System.out.println("数据库连接成功！");
            isDatabaseConnected = true;
        } catch (SQLException e) {
            System.out.println("无法连接到数据库");
            e.printStackTrace();
            isDatabaseConnected = false;
        } catch (ClassNotFoundException e) {
            System.out.println("无法找到数据库驱动类");
            e.printStackTrace();
            isDatabaseConnected = false;
        }
    }
}
