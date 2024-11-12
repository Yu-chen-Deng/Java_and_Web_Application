package com.yuchen.bean;

import java.util.Date;

public class User {
    private int id;
    private String nickname;
    private String username;
    private String password;
    private String sex;

    private String phone;
    private String email;
    private String signature;
    private Date birthday;

    public User() {
    }
    public User(int id, String nickname, String username, String password, String sex, String phone, String email, String signature, Date birthday) {
        this.id = id;
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.signature = signature;
        this.birthday = birthday;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + "'" +
                ", username='" + username + "'" +
                ", password='" + password + "'" +
                ", sex='" + sex + "'" +
                ", phone='" + phone + "'" +
                ", email='" + email + "'" +
                ", signature='" + signature + "'" +
                ", birthday=" + birthday +
                '}';
    }
}
