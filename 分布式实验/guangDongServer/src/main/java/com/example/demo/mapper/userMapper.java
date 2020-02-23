package com.example.demo.mapper;

import com.example.demo.entity.user;

import java.util.List;

public interface userMapper {
    List<user> queryAll();
    user queryUserByAccount(String account);
    int insertUser(user user);
    int updateUser(user user);
    int deleteUser(Integer id);
}
