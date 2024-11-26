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

@WebServlet("/getSelectedPostListServlet")
public class GetSelectedPostListServlet extends HttpServlet {
    ForumService forumService = new ForumService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Forum> selectedPostList =  forumService.getSelectedPostList();
        //以点赞数进行一个排序
        selectedPostList.sort(Comparator.comparingInt(Forum::getUp).reversed());
        req.setAttribute("selectedPostList", selectedPostList);

        //获取会话
        HttpSession httpSession = req.getSession();
        Object user = httpSession.getAttribute("user");

        Object User = new User();
        //根据用户角色不同转发到不同页面
        if (user == null) req.getRequestDispatcher("index.jsp").forward(req, resp);
        else if(user == User) req.getRequestDispatcher("user_page.jsp").forward(req, resp);
        else req.getRequestDispatcher("admin_page.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
