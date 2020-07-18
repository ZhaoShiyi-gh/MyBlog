package com.zhaosy.myblog.dao;

import com.zhaosy.myblog.domain.Blog;
import com.zhaosy.myblog.domain.Setting;

import java.util.List;

public interface BlogDao {
    List<Blog> findUserBlog(String username);

    List<Blog> findUserBlog(String username, int pageNum);

    List<Integer> fidUserSetting(String username);

    Boolean insertBlog(String content, String image, int userId, String headline, int catalogId);

    Boolean insertSetting(int blogid, int catalogid);

    Boolean findsetting(int blogId, int catalogId);

    Boolean deleteBlog(int blogId);

    List<Blog> findAmbiousSearch(String username, String searchContext);

}
