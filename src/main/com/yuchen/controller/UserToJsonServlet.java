package com.yuchen.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.yuchen.bean.User;
import com.yuchen.service.UserService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/userToJsonServlet")
public class UserToJsonServlet extends HttpServlet{
    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        int userId = Integer.parseInt(req.getParameter("userId"));

        // 获取用户数据
        User selectedUser = userService.findUserById(userId);

        // 将用户数据转换为 JSON 字符串
        String json = mapper.writeValueAsString(selectedUser);

        // 设置响应内容类型为 JSON
        resp.setContentType("application/json");

        // 设置响应字符编码
        resp.setCharacterEncoding("UTF-8");

        // 将 JSON 数据写入响应流
        try (PrintWriter out = resp.getWriter()) {
            out.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        //获取会话
//        HttpSession httpSession = req.getSession();
//        Object user = httpSession.getAttribute("user");

        //根据用户角色不同转发到不同页面
//        if (user == null) req.getRequestDispatcher("home.jsp").forward(req, resp);
//        else if(user instanceof User) req.getRequestDispatcher("user_page.jsp").forward(req, resp);
//        else resp.sendRedirect("allPostServlet");
//        req.getRequestDispatcher(String.format("downloadServlet?filename=%d.json", userId)).forward(req, resp);


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
