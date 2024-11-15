package com.yuchen.dao;

import com.yuchen.bean.Reply;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ReplyDao {
    void reply(@Param("replyContent")String replyContent, @Param("replyTime")Date replyTime, @Param("userId")int userId, @Param("fid")int fid);
    //获取某个帖子的所有评论
    List<Reply> getReplyByFid(int fid);
    //给评论点赞
    void replyUp(int replyId);

}
