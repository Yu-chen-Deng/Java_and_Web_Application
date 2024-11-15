package com.yuchen.controller;

import com.yuchen.bean.User;
import com.yuchen.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/upDateUserInfoServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)  // 50MB
public class UpDateUserInfoServlet extends HttpServlet {
    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String t = req.getParameter("t");

        int id = Integer.parseInt(req.getParameter("id"));

        if ("nickname".equals(t)) {
            String nickname = req.getParameter("nickname");
            if (nickname.length() > 16) {
                User user = userService.findUserById(id);
                req.setAttribute("t", t);
                req.setAttribute("update_msg", "用户名过长");
                req.setAttribute("user", user);
                req.getRequestDispatcher("user_info.jsp").forward(req, resp);
                return ;
            }
            userService.update_nickname(nickname, id);
        }
        else if ("password".equals(t)) {
            String password = req.getParameter("password");
            if (password.length() < 6 || password.length() > 20) {
                User user = userService.findUserById(id);
                req.setAttribute("t", t);
                req.setAttribute("update_msg", "密码格式错误");
                req.setAttribute("user", user);
                req.getRequestDispatcher("user_info.jsp").forward(req, resp);
                return ;
            }
            userService.update_password(password, id);
        }
        else if ("birthday".equals(t)) {
            String birthdayString = req.getParameter("birthday");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = null;
            try {
                birthday = formatter.parse(birthdayString);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            userService.update_birthday(birthday, id);
        }
        else if ("sex".equals(t)) {
            userService.update_sex((String) req.getParameter("sex"), id);
        }
        else if ("phone".equals(t)) {
            userService.update_phone((String) req.getParameter("phone"), id);
        }
        else if ("email".equals(t)) {
            userService.update_email((String) req.getParameter("email"), id);
        }
        else  {
            userService.update_signature((String) req.getParameter("signature"), id);
        }

        User user = userService.findUserById(id);

        req.setAttribute("t", t);
        req.setAttribute("user", user);
        req.setAttribute("update_msg", "更新成功");

        req.getRequestDispatcher("user_info.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
