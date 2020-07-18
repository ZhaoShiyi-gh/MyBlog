package com.zhaosy.myblog.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhaosy.myblog.service.UserService;
import com.zhaosy.myblog.service.serviceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AjaxLoginServerlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html, charset=utf-8");
        String username = req.getParameter("username");
        Map<String, Object> map = new HashMap<>();

        UserService us = new UserServiceImpl();
        if (us.findUsename(username)){
            map.put("userexsit", true);
            map.put("msg", "对不起,用户名已存在");
        }else {
            map.put("userexsit", false);
            map.put("msg", "恭喜您,用户名可用");
        }

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(map);
        resp.getWriter().write(s);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
