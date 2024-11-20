package com.yuchen.controller;

import com.yuchen.bean.User;
import com.yuchen.service.AdminUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/getAllUserServlet")
public class GetAllUserServlet extends HttpServlet {
    AdminUserService adminUserService = new AdminUserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
