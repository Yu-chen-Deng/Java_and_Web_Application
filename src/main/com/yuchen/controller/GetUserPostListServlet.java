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

@WebServlet("/getUserPostListServlet")
public class GetUserPostListServlet extends HttpServlet {
    ForumService forumService = new ForumService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        User user = (User)httpSession.getAttribute("user");

        List<Forum> userPostList = forumService.getUserPostList(user.getId());
        userPostList.sort(Comparator.comparingInt(Forum::getFid).reversed());
        int siz = userPostList.size();
        //分页逻辑处理(分块思想)
        int page = 1;//默认页码
        if(req.getParameter("page") != null) page = Integer.parseInt(req.getParameter("page"));//获取当前页数

        int bg = (page - 1) * 10;//每10个帖子为一页
        int ed = Math.min(bg + 10, siz);//防止越界

        userPostList = userPostList.subList(bg, ed);

        req.setAttribute("userPostList", userPostList);
        req.setAttribute("totalPages",  (int)(siz + 9) / 10);//获取总共的页数（上取整）

        req.getRequestDispatcher("user_postList.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
