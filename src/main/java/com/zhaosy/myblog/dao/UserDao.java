package com.zhaosy.myblog.dao;

import com.zhaosy.myblog.domain.User;

public interface UserDao {

//    查找某个用户，返回用户
    User login(String username, String password);

//    查找是否包含某个用户名
    Boolean findUsername(String username);

//    插入用户名，密码和邮箱
    Boolean saveUser(String username, String password, String email);

    int findUserId(String username);
}
