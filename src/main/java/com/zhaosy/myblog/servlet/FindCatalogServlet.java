package com.zhaosy.myblog.servlet;

import com.zhaosy.myblog.domain.Blog;
import com.zhaosy.myblog.service.BlogService;
import com.zhaosy.myblog.service.CatalogService;
import com.zhaosy.myblog.service.serviceImpl.BlogServiceImpl;
import com.zhaosy.myblog.service.serviceImpl.CatalogServicelmpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class FindCatalogServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;utf-8");
        String catalogName = req.getParameter("catalogName");
        catalogName = new String(catalogName.getBytes("ISO-8859-1"), "UTF-8");
        BlogService bs = new BlogServiceImpl();
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        CatalogService cs = new CatalogServicelmpl();
        List<Blog> blogList = bs.findUserBlog(username);
        for (int i = 0, len = blogList.size();i < len ;i++){
            if (blogList.get(i).getCatalogid() != cs.findCatalogId(catalogName)){
                blogList.remove(i);
                i--;
                len--;
            }
        }

        session.setAttribute("name", catalogName);
        session.setAttribute("catalogBlogList", blogList);
        resp.sendRedirect("/myblog/articlePage/showcatalogblog.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
