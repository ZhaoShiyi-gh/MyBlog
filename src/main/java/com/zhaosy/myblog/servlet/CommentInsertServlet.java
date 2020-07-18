package com.zhaosy.myblog.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhaosy.myblog.domain.Blog;
import com.zhaosy.myblog.service.CommentService;
import com.zhaosy.myblog.service.UserService;
import com.zhaosy.myblog.service.serviceImpl.CommentServiceImpl;
import com.zhaosy.myblog.service.serviceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentInsertServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commentContent = request.getParameter("commentContent");
        int commentId = Integer.parseInt(request.getParameter("blogId"));
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        UserService us = new UserServiceImpl();
        Map userInfo = us.findUserInfo(username);
        List<Blog> list = (List<Blog>)userInfo.get("blogs");
        int userid = list.get(0).getUserid();
        CommentService cs = new CommentServiceImpl();
        Boolean flag = cs.commentInsert(commentContent, userid, commentId);

        Map<String, Boolean> map = new HashMap<>();
        if (flag){
            map.put("msg", true);
        }else {
            map.put("msg",false);
        }

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(map);
        response.getWriter().write(s);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
