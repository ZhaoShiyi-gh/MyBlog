package com.zhaosy.myblog.service.serviceImpl;

import com.zhaosy.myblog.dao.CommentDao;
import com.zhaosy.myblog.dao.DaoImpl.CommentDaoImpl;
import com.zhaosy.myblog.domain.Comment;
import com.zhaosy.myblog.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    @Override
    public Boolean commentInsert(String commentContent, int userid, int blogid) {
        Boolean flag =false;
        CommentDao cd = new CommentDaoImpl();
        flag= cd.commentInsert(commentContent, userid, blogid);
        return flag;
    }

    @Override
    public List<Comment> findBlogIdComment(int blogId) {
        CommentDao cd = new CommentDaoImpl();
        List<Comment> list = cd.findBlogIdComment(blogId);
        return list;
    }
}
