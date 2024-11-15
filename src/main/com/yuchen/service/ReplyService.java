package com.yuchen.service;

import com.yuchen.bean.Reply;
import com.yuchen.dao.ReplyDao;
import com.yuchen.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;

public class ReplyService {
    SqlSession sqlSession;
    ReplyDao replyDao;

    public void reply(String replyContent, Date replyTime, int userId, int fid) {
        sqlSession = MybatisUtils.getSqlSession();
        replyDao = sqlSession.getMapper(ReplyDao.class);
        
        replyDao.reply(replyContent, replyTime, userId, fid);
        
        sqlSession.commit();
        
        sqlSession.close();
    }

    //返回某个帖子的所有评论
    public List<Reply> getReplyByFid(int fid) {
        sqlSession = MybatisUtils.getSqlSession();
        replyDao = sqlSession.getMapper(ReplyDao.class);

        List<Reply> replyList = (List<Reply>) replyDao.getReplyByFid(fid);

        sqlSession.close();

        return replyList;
    }

    //给评论点赞
    public void replyUp(int replyId) {
        sqlSession = MybatisUtils.getSqlSession();
        replyDao = sqlSession.getMapper(ReplyDao.class);

        replyDao.replyUp(replyId);

        sqlSession.commit();

        sqlSession.close();
    }
}
