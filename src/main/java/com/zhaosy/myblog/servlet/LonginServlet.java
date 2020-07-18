package com.zhaosy.myblog.servlet;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhaosy.myblog.dao.DaoImpl.UserDaoImpl;
import com.zhaosy.myblog.domain.User;
import com.zhaosy.myblog.service.UserService;
import com.zhaosy.myblog.service.serviceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LonginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");  //改变参数的编码格式
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserService us = new UserServiceImpl();
        User user = us.login(username, password);
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session = req.getSession();

        Map<String, Boolean> map = new HashMap<>();
        if (user != null){
            session.setAttribute("user", user);
            session.setAttribute("username", username);
//            resp.sendRedirect("main.jsp");
            map.put("msg", true);
            req.getRequestDispatcher("searchInformationServlet").forward(req, resp);
        }else {
//            map.put("msg", false);
            resp.getWriter().write("用户名或密码错误");
//            session.setAttribute("erro", "登录失败");
//            resp.sendRedirect("login.jsp");
        }

/*        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(map);
        resp.getWriter().write(s);*/


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doPost(req, resp);
    }
}
