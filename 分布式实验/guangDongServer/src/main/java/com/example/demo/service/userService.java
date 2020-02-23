package com.example.demo.service;

import com.example.demo.entity.user;

import java.util.List;

public interface userService {
    List<user> queryAll();
    user queryUserByAccount(String account);
    int insertUser(user user);
    int updateUser(user user);
    int deleteUser(Integer id);
}
