package com.zhaosy.myblog.service.serviceImpl;

import com.zhaosy.myblog.dao.BlogDao;
import com.zhaosy.myblog.dao.CatalogDao;
import com.zhaosy.myblog.dao.DaoImpl.BlogDaoImpl;
import com.zhaosy.myblog.dao.DaoImpl.CatalogDaoImpl;
import com.zhaosy.myblog.dao.DaoImpl.UserDaoImpl;
import com.zhaosy.myblog.dao.UserDao;
import com.zhaosy.myblog.domain.Blog;
import com.zhaosy.myblog.domain.Catalog;
import com.zhaosy.myblog.domain.User;
import com.zhaosy.myblog.service.BlogService;
import com.zhaosy.myblog.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    @Override
    public User login(String username, String password) {
        UserDao user = new UserDaoImpl();
        return user.login(username, password);
    }

    @Override
    public Boolean findUsename(String username) {
        UserDao userName = new UserDaoImpl();
        return userName.findUsername(username);
    }

    @Override
    public Boolean registerUser(String username, String password, String email) {
        UserDao userdao = new UserDaoImpl();
        return userdao.saveUser(username, password, email);
    }

    @Override
    public Map findUserInfo(String username) {
        Map map = new HashMap();
        BlogService bs = new BlogServiceImpl();
        List<Blog> list1 = bs.findUserBlog(username);
        map.put("blogs", list1);
        CatalogDao cd = new CatalogDaoImpl();
        map.put("userCatalog",cd.findUserCatalog(username));
        return map;
    }
}
