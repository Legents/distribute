package com.example.demo.serviceImpl;

import com.example.demo.entity.user;
import com.example.demo.mapper.userMapper;
import com.example.demo.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service(value="rmiUserService1")
public class userServiceImpl implements userService {
    @PostConstruct
    public void initMethod(){
        System.out.println("初始化方法时调用");
    }

    @Autowired
    private userMapper userMapper;
    @Override
    public List<user> queryAll() {
        return userMapper.queryAll();
    }

    @Override
    @Cacheable(value = "c1")
    public user queryUserByAccount(String account) {
        System.out.println("从上海服务器查询车主");
        return userMapper.queryUserByAccount(account);
    }

    @Transactional
    @Override
    public int insertUser(user user) {
        int i=userMapper.insertUser(user);
        return i;
    }

    @Transactional
    @Override
    public int updateUser(user user) {
      int i=userMapper.updateUser(user);
      return i;
    }

    @Override
    public int deleteUser(Integer id) {
        int i=userMapper.deleteUser(id);
        return i;
    }
}
