package com.zhaosy.myblog.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AjaxSpitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html, charset=utf-8");
        HttpSession session = request.getSession();
        int pageNum = (int) session.getAttribute("pageNum");
        Map<String, Integer> map = new HashMap<>();
        map.put("pageNum", pageNum);

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(map);
        response.getWriter().write(s);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
