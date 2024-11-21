package com.yuchen.controller;

import com.yuchen.bean.Forum;
import com.yuchen.service.AdminUserService;
import com.yuchen.service.ForumService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet("/adminDeletePostServlet")
public class AdminDeletePostServlet extends HttpServlet {
    AdminUserService adminUserService = new AdminUserService();
    ForumService forumService = new ForumService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int fid = Integer.parseInt(req.getParameter("fid"));

        adminUserService.deletePost(fid);

        List<Forum> allpostList = forumService.getPostList();
        allpostList.sort(Comparator.comparingInt(Forum::getFid).reversed());
        //帖子数量
        int siz = allpostList.size();

        //分页逻辑处理(分块思想)
        int page = 1;//默认页码
        if(req.getParameter("page") != null) page = Integer.parseInt(req.getParameter("page"));//获取当前页数

        int bg = (page - 1) * 10;//每10个帖子为一页
        int ed = Math.min(bg + 10, siz);//防止越界

        List<Forum> postList = allpostList.subList(bg, ed);
        req.setAttribute("postList", postList);
        req.setAttribute("totalPages",  (int)(siz + 9) / 10);//获取总共的页数（上取整）
        req.setAttribute("del_msg", "删帖成功");
        String URL = req.getParameter("URL");
        req.getRequestDispatcher(URL).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
