package com.yuchen.controller;

import com.yuchen.bean.AdminUser;
import com.yuchen.bean.User;
import com.yuchen.service.LoginService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private LoginService loginService = new LoginService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");
        //获取会话
        HttpSession session = req.getSession();

        int type = Integer.parseInt(req.getParameter("type"));

        if (type == 2) {
            AdminUser adminUser = loginService.adminLogin(username, password);
            if (adminUser == null) {
                req.setAttribute("login_msg","账号或密码错误");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);

                return ;
            }

            session.setAttribute("user", adminUser);
            if ("1".equals(remember)) {
                dealCookie(username, password, resp);
            }

            resp.sendRedirect("indexServlet");
        }
        else {
            User user = loginService.login(username, password);
            if (user == null) {
                req.setAttribute("login_msg","账号或密码错误");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }

            session.setAttribute("user", user);
            if ("1".equals(remember)) {
                dealCookie(username, password, resp);
            }
            resp.sendRedirect("indexServlet");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    private void dealCookie(String username, String password, HttpServletResponse response) {
        // 创建 Cookie 对象并设置用户名和密码
        Cookie c_username = new Cookie("username", username);
        Cookie c_password = new Cookie("password", password);

        // 设置 Cookie 的存活时间，例如 7 天
        c_username.setMaxAge(60 * 60 * 24 * 7);
        c_password.setMaxAge(60 * 60 * 24 * 7);

        // 发送 Cookie 到客户端
        response.addCookie(c_username);
        response.addCookie(c_password);
    }
}
