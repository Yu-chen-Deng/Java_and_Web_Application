package com.yuchen.dao;

import com.yuchen.bean.Forum;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ForumDao {
    //获取全部帖子
    List<Forum> getPostList();
    //获取帖子信息
    Forum post_info(int fid);
    //发布帖子
    void post(@Param("title") String title, @Param("content") String content, @Param("createTime") Date createTime, @Param("userId") int userId);
    //获取精选贴
    List<Forum> getSelectedPostList();
    //点赞
    int like(int fid);
    //更新评论数
    void replyTot(int fid);
    //获取某个用户的所有帖子
    List<Forum> getUserPostList(int id);
    //删除帖子
    int deletePost(int fid);
    //更改帖子
    int updatePost(@Param("title") String title, @Param("content") String content, @Param("fid") int fid);
    //删除某个用户的所有帖子
    void deleteUserAllPost(int userId);
    //设置帖子为精选帖
    void setSelectedPost(int fid);
    //取消帖子为精选帖
    void unsetSelectedPost(int fid);
}
