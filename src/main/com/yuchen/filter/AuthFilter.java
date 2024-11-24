package com.yuchen.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/post.jsp", "/reply.jsp", "/upServlet", "/replyUpServlet"})
public class AuthFilter implements jakarta.servlet.Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //将 request 对象强转成 HttpServletRequest 对象
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        
        //获取 session 对象
        HttpSession httpSession = servletRequest.getSession();
        
        //从 session 对象中获取用户信息
        Object user = httpSession.getAttribute("user");
        
        if (user != null) {
            chain.doFilter(request, response);
        }
        else {
            request.setAttribute("login_msg", "您尚未登录，请登录！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
