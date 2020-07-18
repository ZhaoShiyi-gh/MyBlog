package com.zhaosy.myblog.servlet;

import com.zhaosy.myblog.service.CatalogService;
import com.zhaosy.myblog.service.serviceImpl.CatalogServicelmpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddNewCatalog extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String name = req.getParameter("insertCatalogName");
        name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
        CatalogService cs = new CatalogServicelmpl();
        Boolean flag = cs.insertCatalog(name, null);
        if (flag){
            resp.getWriter().write("新增分类成功");
        }else {
            resp.getWriter().write("新增分类失败");

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
