package com.zhaosy.myblog.service;

import com.zhaosy.myblog.domain.Blog;
import com.zhaosy.myblog.domain.User;

import java.util.List;

public interface BlogService {
    List<Blog> findUserBlog(String username);

    List<Blog> findUserBlog(String username, int pageNum);

    Boolean addBlog(String content,String image,String headline, User user, String catalogName);

    Boolean deleteBlog(int blogId);

    List<Blog> findAmbiousSearch(String username, String searchContext);
}

