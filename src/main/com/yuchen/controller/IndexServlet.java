package com.yuchen.controller;

import com.yuchen.bean.Forum;
import com.yuchen.bean.User;
import com.yuchen.service.ForumService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet("/indexServlet")
public class IndexServlet extends HttpServlet {
    ForumService forumService = new ForumService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取精选帖子
        List<Forum> selectedPostList =  forumService.getSelectedPostList();

        //以点赞数从多到少进行一个排序
        selectedPostList.sort(Comparator.comparingInt(Forum::getUp).reversed());

        //获取所有帖子
        List<Forum> recentPostList = forumService.getPostList();
        //以帖子ID从大到小进行一个排序（即发布时间由晚到早）
        recentPostList.sort(Comparator.comparingInt(Forum::getFid).reversed());

        req.setAttribute("selectedPostList", selectedPostList);
        req.setAttribute("recentPostList", recentPostList);

        //获取会话
        HttpSession httpSession = req.getSession();
        Object user = httpSession.getAttribute("user");

        //根据用户角色不同转发到不同页面
        if (user == null) req.getRequestDispatcher("home.jsp").forward(req, resp);
        else if(user instanceof User) req.getRequestDispatcher("user_page.jsp").forward(req, resp);
        else resp.sendRedirect("allPostServlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
