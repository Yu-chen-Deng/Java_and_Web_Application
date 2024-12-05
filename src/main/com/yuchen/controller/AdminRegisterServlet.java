package com.yuchen.controller;

import com.yuchen.bean.User;
import com.yuchen.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/adminRegisterServlet")
public class AdminRegisterServlet extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求体的字符编码，防止中文乱码
        req.setCharacterEncoding("UTF-8");

        // 从请求体中读取JSON字符串
        StringBuilder jsonStringBuilder = new StringBuilder();
        String line;
        try (BufferedReader reader = req.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonStringBuilder.append(line);
            }
        }
        String json = jsonStringBuilder.toString();

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(json, User.class);

        // 从user对象中获取用户名、昵称和密码
        String username = user.getUsername();
        String nickname = user.getNickname();
        String password = user.getPassword();

        //检查用户名长度
        if (nickname.length() > 16) {
            req.setAttribute("delU_msg", "用户名过长");
            req.getRequestDispatcher("allUser.jsp").forward(req, resp);
            return ;
        }
        //检查账号长度
        if (username.length() < 6 || username.length() > 20 ){
            req.setAttribute("delU_msg", "账号格式错误");
            req.getRequestDispatcher("allUser.jsp").forward(req, resp);

            return ;
        }
        //检查密码长度
        if (password.length() < 6 || password.length() > 20) {
            req.setAttribute("delU_msg", "密码格式错误");
            req.getRequestDispatcher("allUser.jsp").forward(req, resp);
            return ;
        }

        if (!userService.checkUser(username)) {
            System.out.println(username);
            req.setAttribute("delU_msg", "账号已存在");
            req.getRequestDispatcher("allUser.jsp").forward(req, resp);

            return ;
        }

        userService.register(user);
        req.setAttribute("delU_msg", "注册成功");
        req.getRequestDispatcher("allUser.jsp").forward(req, resp);

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
