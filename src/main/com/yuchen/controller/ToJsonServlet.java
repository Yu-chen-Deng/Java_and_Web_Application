package com.yuchen.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.yuchen.bean.User;
import com.yuchen.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/toJsonServlet")
public class ToJsonServlet extends HttpServlet{
    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        int userId = Integer.parseInt(req.getParameter("userId"));

        User user = userService.findUserById(userId);

        String json = mapper.writeValueAsString(user);
        System.out.println(json);

        req.getRequestDispatcher("getAllUserServlet").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
