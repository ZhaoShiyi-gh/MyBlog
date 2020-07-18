package com.zhaosy.myblog.servlet;

import com.zhaosy.myblog.domain.Blog;
import com.zhaosy.myblog.domain.Catalog;
import com.zhaosy.myblog.domain.Comment;
import com.zhaosy.myblog.domain.User;
import com.zhaosy.myblog.service.BlogService;
import com.zhaosy.myblog.service.CommentService;
import com.zhaosy.myblog.service.UserService;
import com.zhaosy.myblog.service.serviceImpl.BlogServiceImpl;
import com.zhaosy.myblog.service.serviceImpl.CommentServiceImpl;
import com.zhaosy.myblog.service.serviceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchInformationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        /*BlogService bs = new BlogServiceImpl();
        HttpSession session = req.getSession();
        List list = bs.findUserBlog((String) session.getAttribute("username"));
        session.setAttribute("blogList", list);
        resp.sendRedirect("main.jsp");*/

        UserService us = new UserServiceImpl();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Map map = us.findUserInfo(user.getUsername());
        List<Catalog> list = (List<Catalog>) map.get("userCatalog");
        CommentService cs = new CommentServiceImpl();

        List<Blog> blogs = (List<Blog>) map.get("blogs");
        Map<Integer, List<Comment>> map1 = new HashMap<>();
        for (Blog blog:
             blogs) {
            map1.put(blog.getBlogid(), cs.findBlogIdComment(blog.getBlogid()));
        }


        session.setAttribute("commentMap", map1);
        session.setAttribute("userMap", map);
        resp.sendRedirect("main.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
