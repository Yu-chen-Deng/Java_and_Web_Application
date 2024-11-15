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

@WebServlet("/postInfoServlet")
public class PostInfoServlet extends HttpServlet {
    ForumService forumService = new ForumService();
    ReplyService replyService = new ReplyService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int fid = Integer.parseInt(req.getParameter("fid"));

        Forum postInfo = forumService.post_info(fid);


        List<Reply> allpostList = replyService.getReplyByFid(fid);
        allpostList.sort(Comparator.comparingInt(Reply::getReplyId).reversed());

        int siz = allpostList.size();

        int page = 1;
        if(req.getParameter("page") != null) page = Integer.parseInt(req.getParameter("page"));

        int bg = (page - 1) * 10;
        int ed = Math.min(bg + 10, siz);

        List<Reply> replyList = allpostList.subList(bg, ed);

        req.setAttribute("replyList", replyList);
        req.setAttribute("totalPages",  (int)(siz + 9) / 10);
        req.setAttribute("postInfo", postInfo);

        String URL = "postInfo.jsp";

        if (req.getAttribute("up_msg") != null) {
            URL = "reply.jsp";
            req.setAttribute("up_msg", "点赞成功");
            int upId = (int) req.getAttribute("upId");
            req.setAttribute("upId", upId);
        }

        req.getRequestDispatcher(URL).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
