package com.zhaosy.myblog.servlet;

import com.zhaosy.myblog.domain.Blog;
import com.zhaosy.myblog.service.BlogService;
import com.zhaosy.myblog.service.serviceImpl.BlogServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SplitPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNum = request.getParameter("pageNum");
        int i = Integer.parseInt(pageNum);
        HttpSession session = request.getSession();
        session.setAttribute("pageNum", i);
        String username = (String)session.getAttribute("username");
        BlogService bs = new BlogServiceImpl();
        List<Blog> userBlog = bs.findUserBlog(username, i);

        session.setAttribute("userBlog", userBlog);

        response.sendRedirect("/myblog/articlePage/allarticle.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
