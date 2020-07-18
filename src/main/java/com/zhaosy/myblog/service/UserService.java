package com.zhaosy.myblog.service;

import com.zhaosy.myblog.domain.User;

import java.util.Map;

public interface UserService {
    User login(String username, String password);

    Boolean findUsename(String username);

    Boolean registerUser(String username, String password, String email);

    Map findUserInfo(String username);
}
