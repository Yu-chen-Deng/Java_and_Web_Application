package com.yuchen.controller;

import com.yuchen.bean.User;
import com.yuchen.service.ForumService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/postServlet")
public class PostServlet extends HttpServlet {
    private ForumService forumService = new ForumService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取当前登录用户信息
        HttpSession httpSession = req.getSession();
        User user = (User) httpSession.getAttribute("user");

        int userId = user.getId();
        String title = req.getParameter("title");
        String subtitle = req.getParameter("subtitle");
        String content = req.getParameter("content");
        Date date= new Date();  
        String nowTime= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        Timestamp createTime = Timestamp.valueOf(nowTime);//时间转换 java.util.Date是java.sql.Date的父类
        
        forumService.post(title, subtitle, content, createTime, userId);

        req.setAttribute("post_msg", "发帖成功！");
        req.getRequestDispatcher("getUserPostListServlet").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
