package com.yuchen.controller;

import org.apache.commons.io.IOUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //得到下载的文件
        String filename = request.getParameter("filename");
        System.out.println(filename);

        String mimeType = request.getServletContext().getMimeType(filename);
        response.setContentType(mimeType);
        //设置响应头,考虑汉字问题,对文件使用URL编码
        response.setHeader("content-disposition","attachment;filename="
                + URLEncoder.encode(filename,"utf-8"));
        //设置上下文域
        ServletContext application = getServletContext();
        //获取要下载资源的目录的字节输入流对象
        InputStream is = application.getResourceAsStream("/download/" + filename);
        //获取字符输出流
        ServletOutputStream os = response.getOutputStream();
        //拷贝
        IOUtils.copy(is,os);
        //关流
        is.close();
        os.close();

        req.getRequestDispatcher("getAllUserServlet").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
