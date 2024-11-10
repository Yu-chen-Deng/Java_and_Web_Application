package com.yuchen.bean;

import java.io.Serializable;

public class AdminUser {
    private int id;
    private String nickname;
    private String username;
    private String password;

    public AdminUser(){

    }

    public AdminUser(int id, String nickname, String username, String password) {
        this.id = id;
        this.nickname = nickname;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AdminUser{" +
                "id=" + id +
                ", nickname='" + nickname + "'" +
                ", username='" + username + "'" +
                ", password='" + password + "'" +
                '}';
    }
}
