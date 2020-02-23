package com.example.demo.serviceImpl;

import com.example.demo.entity.user;
import com.example.demo.mapper.policeMapper;
import com.example.demo.service.policeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class policeServiceImpl implements policeService {

    @Autowired
    private policeMapper policeMapper;
    @Override
    @Cacheable(value="c1")
    public user queryUserByAccount(String account) {
        System.out.println("从上海服务器查询警察");
        user user=policeMapper.queryUserByAccount(account);
        return user;
    }
}
