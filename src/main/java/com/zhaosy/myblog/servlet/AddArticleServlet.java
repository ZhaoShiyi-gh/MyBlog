package com.zhaosy.myblog.servlet;

import com.zhaosy.myblog.domain.User;
import com.zhaosy.myblog.service.BlogService;
import com.zhaosy.myblog.service.serviceImpl.BlogServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddArticleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String content = req.getParameter("content");
        String catalogName = req.getParameter("catalogName");
        String headline = req.getParameter("headline");
        BlogService bs = new BlogServiceImpl();

        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        boolean flag = bs.addBlog(content,"null", headline, user, catalogName);
        if (flag) {
            req.getRequestDispatcher("/articlePage/successadd.jsp").forward(req, resp);
        }else {
            resp.getWriter().write("发表失败");

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doPost(req, resp);
    }
}
