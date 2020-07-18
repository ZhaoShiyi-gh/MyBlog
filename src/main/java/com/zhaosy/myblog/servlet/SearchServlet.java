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

public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;utf-8");
        String searchContext = request.getParameter("searchContext");
        searchContext = new String(searchContext.getBytes("ISO-8859-1"), "UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        BlogService bs = new BlogServiceImpl();
        List<Blog> ambiousSearch = bs.findAmbiousSearch(username, searchContext);
        session.setAttribute("searchResult", ambiousSearch);
        response.sendRedirect("/myblog/articlePage/ambiousearch.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
