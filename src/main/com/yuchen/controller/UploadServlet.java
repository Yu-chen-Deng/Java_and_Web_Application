package com.yuchen.controller;

import com.yuchen.bean.User;
import com.yuchen.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/uploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet{
    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取当前登录用户信息
        HttpSession httpSession = req.getSession();
        User user = (User) httpSession.getAttribute("user");

        if (user == null) {
            resp.sendRedirect("/login.jsp"); // 如果用户没有登录，重定向到登录页面
            return;
        }

        req.setCharacterEncoding("utf-8");

        //获取Part对象 （Servlet 将 mutipart/form-data 的 POST 请求封装成 Part对象）
        Part part = req.getPart("file");
        //通过Part对象得到上传的文件名
        // String fileName = part.getSubmittedFileName();
        String fileName = "/avatar/" + String.format("%d", user.getId()) + ".jpg";
        //得到文件存放的路径
        String filePath = req.getServletContext().getRealPath("/");
        part.write(filePath + fileName);

        // 更新用户头像，调用 UpDateUserInfoServlet 更新数据库中的 avatar 字段
        // 重定向到更新头像的 servlet

        resp.sendRedirect("/upDateUserInfoServlet?t=avatar&id=" + user.getId() + "&avatar=" + fileName);
//        req.getRequestDispatcher("getAllUserServlet").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
