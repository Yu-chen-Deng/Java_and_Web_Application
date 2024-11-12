package com.yuchen.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Reply implements Serializable {
    private int replyId;
    private int up;
    private int userId;
    private int fid;
    private String replyContent;
    private Timestamp replyTime;
    private User author = new User();

    public Reply() {

    }

    public Reply(int replyId, int up, int userId, int fid, String replyContent, Timestamp replyTime, User author) {
        this.replyId = replyId;
        this.up = up;
        this.userId = userId;
        this.fid = fid;
        this.replyContent = replyContent;
        this.replyTime = replyTime;
        this.author = author;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Timestamp getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Timestamp replyTime) {
        this.replyTime = replyTime;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "replyId=" + replyId +
                ", up=" + up +
                ", userId=" + userId +
                ", fid=" + fid +
                ", replyContent='" + replyContent + "'" +
                ", replyTime=" + replyTime +
                ", author=" + author +
                '}';
    }
}
