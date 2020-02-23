package com.example.demo.mapper;

import com.example.demo.entity.user;

public interface policeMapper {
    user queryUserByAccount(String account);
}
