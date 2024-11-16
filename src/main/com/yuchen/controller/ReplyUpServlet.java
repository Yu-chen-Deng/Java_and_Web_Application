package com.yuchen.controller;

import com.yuchen.bean.Forum;
import com.yuchen.bean.Reply;
import com.yuchen.service.ForumService;
import com.yuchen.service.ReplyService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet("/replyUpServlet")
public class ReplyUpServlet extends HttpServlet {
    ReplyService replyService = new ReplyService();
    ForumService forumService = new ForumService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int replyId = Integer.parseInt(req.getParameter("replyId"));
        int fid = Integer.parseInt(req.getParameter("fid"));

        replyService.replyUp(replyId);

        Forum postInfo = forumService.post_info(fid);
        //获取全部帖子信息
        List<Reply> allpostList = replyService.getReplyByFid(fid);
        allpostList.sort(Comparator.comparingInt(Reply::getReplyId).reversed());
        //帖子数量
        int siz = allpostList.size();
        //分页逻辑处理(分块思想)
        int page = 1;//默认页码
        if(req.getParameter("page") != null) page = Integer.parseInt(req.getParameter("page"));//获取当前页数

        int bg = (page - 1) * 10;//每10个帖子为一页
        int ed = Math.min(bg + 10, siz);//防止越界

        List<Reply> replyList = allpostList.subList(bg, ed);


        req.setAttribute("up_msg", "点赞成功");
        req.setAttribute("upId", replyId);
        req.setAttribute("replyList", replyList);
        req.setAttribute("totalPages",  (int)(siz + 9) / 10);//获取总共的页数（上取整）
        req.setAttribute("postInfo", postInfo);

        req.getRequestDispatcher("reply.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
