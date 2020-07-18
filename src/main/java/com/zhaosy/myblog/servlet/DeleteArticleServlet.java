package com.zhaosy.myblog.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhaosy.myblog.service.BlogService;
import com.zhaosy.myblog.service.serviceImpl.BlogServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeleteArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int blogId = Integer.parseInt(request.getParameter("blogId"));
        BlogService bs = new BlogServiceImpl();
        Boolean flag = bs.deleteBlog(blogId);

        request.getRequestDispatcher("/showAllServlet").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
