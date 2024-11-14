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

@WebServlet("/allPostServlet")
public class AllPostServlet extends HttpServlet {
    private ForumService forumService = new ForumService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取全部帖子信息
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

        //获取会话,根据用户角色不同转发到不同页面
        HttpSession httpSession = req.getSession();
        Object user = httpSession.getAttribute("user");
        Object User = new User();

        if (user == null) req.getRequestDispatcher("post_square.jsp").forward(req, resp);
        else if(user instanceof User) req.getRequestDispatcher("user_post_square.jsp").forward(req, resp);
        else req.getRequestDispatcher("admin_page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
