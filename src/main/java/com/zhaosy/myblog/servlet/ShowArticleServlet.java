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

public class ShowArticleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;utf-8");
        String s = req.getParameter("blogId");
        s = new String(s.getBytes("ISO-8859-1"), "UTF-8");

        int blogId = Integer.parseInt(s);
        HttpSession session = req.getSession();
        BlogService bs = new BlogServiceImpl();
        List<Blog> blogList = bs.findUserBlog((String) session.getAttribute("username"));
        for (Blog blog:
             blogList) {
            if (blog.getBlogid() == blogId){
                session.setAttribute("showBlog", blog);
                break;
            }
        }
        resp.sendRedirect("/myblog/articlePage/show.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
