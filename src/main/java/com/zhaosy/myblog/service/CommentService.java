package com.zhaosy.myblog.service;

import com.zhaosy.myblog.domain.Comment;

import java.util.List;

public interface CommentService {
    Boolean commentInsert(String commentContent, int userid, int blogid);

    List<Comment> findBlogIdComment(int blogId);
}
