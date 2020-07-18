package com.zhaosy.myblog.servlet;

import com.zhaosy.myblog.service.UserService;
import com.zhaosy.myblog.service.serviceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html, charset=utf-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        UserService us = new UserServiceImpl();
        Boolean flag = us.registerUser(username, password, email);
        if (flag){

            resp.getWriter().write("Register Success......");
        }else{
            resp.getWriter().write("Reguster Failed......");
        }

        resp.sendRedirect("login.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
