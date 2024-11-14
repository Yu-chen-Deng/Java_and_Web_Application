package com.yuchen.controller;

import com.yuchen.service.ForumService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/upServlet")
public class UpServlet extends HttpServlet {
    ForumService forumService = new ForumService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fid = req.getParameter("fid");

        forumService.like(Integer.parseInt(fid));

        String URL = req.getParameter("URL") + "?fid=" + fid;

        //判断是否是作者主页发出的指令
        if (req.getParameter("id") != null) {
            URL += "&id=" + req.getParameter("id");
        }

        int f = Integer.parseInt(fid);
        req.setAttribute("up_msg", "点赞成功");
        req.setAttribute("upId", f);
        req.getRequestDispatcher(URL).forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
