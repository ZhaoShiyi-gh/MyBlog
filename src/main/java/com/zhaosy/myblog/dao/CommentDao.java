package com.zhaosy.myblog.dao;

import com.zhaosy.myblog.domain.Comment;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CommentDao {
    Boolean commentInsert(String commentContent, int userid, int blogid);

    List<Comment> findBlogIdComment(int blogId);

}
