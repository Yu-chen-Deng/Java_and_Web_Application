package com.yuchen.controller;

import com.yuchen.bean.Forum;
import com.yuchen.bean.Reply;
import com.yuchen.bean.User;
import com.yuchen.service.ForumService;
import com.yuchen.service.ReplyService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@WebServlet("/replyServlet")
public class ReplyServlet extends HttpServlet {
    private ReplyService replyService = new ReplyService();
    private ForumService forumService = new ForumService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        
        User user = (User) httpSession.getAttribute("user");
        int userId = user.getId();
        int fid = Integer.parseInt(req.getParameter("fid"));

        //评论操作
        String replyContent = req.getParameter("reply_content");
        Date date= new Date();
        String nowTime= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        Timestamp replyTime = Timestamp.valueOf(nowTime);//时间转换 java.util.Date是java.sql.Date的父类
        replyService.reply(replyContent, replyTime, userId, fid);
        forumService.replyTot(fid);

        //重新获取数据，保证转发后数据还存在
        List<Reply> replyList = replyService.getReplyByFid(fid);
        Forum postInfo = forumService.post_info(fid);
        //以帖子ID从大到小进行一个排序（即发布时间由晚到早）
        replyList.sort(Comparator.comparingInt(Reply::getReplyId).reversed());

        req.setAttribute("postInfo", postInfo);
        req.setAttribute("replyList", replyList);
        req.setAttribute("reply_msg", "回复成功");

        req.getRequestDispatcher("reply.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
