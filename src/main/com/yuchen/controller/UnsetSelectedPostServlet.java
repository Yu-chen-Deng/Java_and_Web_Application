package com.yuchen.controller;

import com.yuchen.service.AdminUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/unsetSelectedPostServlet")
public class UnsetSelectedPostServlet extends HttpServlet {
    AdminUserService adminUserService = new AdminUserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int fid = Integer.parseInt(req.getParameter("fid"));

        adminUserService.unsetSelectedPost(fid);

        req.setAttribute("fid", fid);
        req.setAttribute("set_msg","取消精选帖成功");

        req.getRequestDispatcher("allPostServlet").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
