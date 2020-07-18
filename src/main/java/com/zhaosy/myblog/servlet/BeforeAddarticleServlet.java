package com.zhaosy.myblog.servlet;

import com.zhaosy.myblog.domain.Catalog;
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
import java.util.ArrayList;
import java.util.List;

public class BeforeAddarticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;utf-8");
        CatalogService cs = new CatalogServicelmpl();
        List<Catalog> list = cs.findCatalog();
        HttpSession session = request.getSession();
        session.setAttribute("catalog", list);
        response.sendRedirect("/myblog/articlePage/addArticle.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
