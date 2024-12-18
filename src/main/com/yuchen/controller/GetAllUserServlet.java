package com.yuchen.controller;

import com.yuchen.bean.User;
import com.yuchen.listener.OnlineUserListener;
import com.yuchen.service.AdminUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet("/getAllUserServlet")
public class GetAllUserServlet extends HttpServlet {
    AdminUserService adminUserService = new AdminUserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取在线用户列表
        Map<Integer, User> onlineUsers = OnlineUserListener.getOnlineUsers();
        int count = OnlineUserListener.getOnlineUserCount();

        // 存入 request 作用域
        req.setAttribute("onlineUsers", onlineUsers.values());
        req.setAttribute("onlineCount", count);


        List<User> userList = adminUserService.getAllUser();
        int siz = userList.size();

        int page = 1;//默认页码
        if(req.getParameter("page") != null) page = Integer.parseInt(req.getParameter("page"));//获取当前页数
        int bg = (page - 1) * 10;//每10个帖子为一页
        int ed = Math.min(bg + 10, siz);//防止越界

        userList = userList.subList(bg, ed);
        req.setAttribute("userList", userList);
        req.setAttribute("totalPages",  (int)(siz + 9) / 10);

        req.getRequestDispatcher("allUser.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
