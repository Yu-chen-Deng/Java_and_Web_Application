package com.yuchen.controller;

import com.yuchen.bean.User;
import com.yuchen.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nickname = req.getParameter("nickname");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //检查用户名长度
        if (nickname.length() > 16) {
            req.setAttribute("register_msg", "用户名过长");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return ;
        }
        //检查账号长度
        if (username.length() < 6 || username.length() > 20 ){
            req.setAttribute("register_msg", "账号格式错误");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);

            return ;
        }
        //检查密码长度
        if (password.length() < 6 || password.length() > 20) {
            req.setAttribute("register_msg", "密码格式错误");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return ;
        }

        // 获取用户输入的验证码
        String checkCode = req.getParameter("checkCode");
        // 程序生成的验证码，从Session获取
        HttpSession session = req.getSession();
        String checkCodeGen = (String) session.getAttribute("checkCodeGen");
        if (!checkCodeGen.equalsIgnoreCase(checkCode)) {//验证不对
            req.setAttribute("checkCode_msg", "输入验证错误，请重新输入");
            //验证码输入不对回到注册界面，并提示
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            // 不允许注册
            return;
        }

        if (!userService.checkUser(username)) {
            System.out.println(username);
            req.setAttribute("register_msg", "账号已存在");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);

            return ;
        }

        User user = new User();
        
        user.setNickname(nickname);
        user.setUsername(username);
        user.setPassword(password);
        userService.register(user);
        req.setAttribute("register_msg", "注册成功， 请登录");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
