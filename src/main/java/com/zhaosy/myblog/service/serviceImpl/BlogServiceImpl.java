package com.zhaosy.myblog.service.serviceImpl;

import com.zhaosy.myblog.dao.BlogDao;
import com.zhaosy.myblog.dao.CatalogDao;
import com.zhaosy.myblog.dao.DaoImpl.BlogDaoImpl;
import com.zhaosy.myblog.dao.DaoImpl.CatalogDaoImpl;
import com.zhaosy.myblog.dao.DaoImpl.UserDaoImpl;
import com.zhaosy.myblog.dao.UserDao;
import com.zhaosy.myblog.domain.Blog;
import com.zhaosy.myblog.domain.User;
import com.zhaosy.myblog.service.BlogService;

import java.util.ArrayList;
import java.util.List;

public class BlogServiceImpl implements BlogService {
    @Override
    public List<Blog> findUserBlog(String username) {
        BlogDao bd = new BlogDaoImpl();
        List<Blog> list = new ArrayList<>();
        list = bd.findUserBlog(username);
        return list;
    }

    @Override
    public List<Blog> findUserBlog(String username, int pageNum) {
        BlogDao bd = new BlogDaoImpl();
        List<Blog> list = new ArrayList<>();
        list = bd.findUserBlog(username, pageNum);
        return list;
    }

    @Override
    public Boolean addBlog(String content,String image, String headline, User user, String catalogName) {
        BlogDao bd = new BlogDaoImpl();
        UserDao us = new UserDaoImpl();
        CatalogDao cd = new CatalogDaoImpl();
        int catalogId = cd.findCatalogId(catalogName);
        int userId = us.findUserId(user.getUsername());
        Boolean flag = bd.insertBlog(content, image, userId ,headline,catalogId);
        List<Blog> list = findUserBlog(user.getUsername());
        for (Blog blog:
             list) {
            int blogId = blog.getBlogid();
            if (!bd.findsetting(blogId,catalogId)){
                flag = bd.insertSetting(blogId,catalogId);
                break;
            }
        }
        return flag;
    }

    @Override
    public Boolean deleteBlog(int blogId) {
        BlogDao bd = new BlogDaoImpl();
        return bd.deleteBlog(blogId);
    }

    @Override
    public List<Blog> findAmbiousSearch(String username, String searchContext) {
        List<Blog> list = new ArrayList<>();
        BlogDao bd = new BlogDaoImpl();
        list = bd.findAmbiousSearch(username, searchContext);
        return list;
    }
}
