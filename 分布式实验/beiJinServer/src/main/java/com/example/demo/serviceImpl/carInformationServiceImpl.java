package com.example.demo.serviceImpl;

import com.example.demo.entity.carInformation;
import com.example.demo.mapper.carInformationMapper;
import com.example.demo.service.carInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "rmiCarService1")
public class carInformationServiceImpl implements carInformationService {
    @Autowired
    private carInformationMapper carInformationMapper;
    @Override
    public List<carInformation> queryAll() {
        return carInformationMapper.queryAll();
    }

    @Override
    @Cacheable(value = "c22")
    public carInformation queryByAccount(String account) {
        System.out.println("从北京服务器查询车辆信息");
        return carInformationMapper.queryByAccount(account);
    }

    @Transactional
    @Override
    @CachePut(value = "c22")
    public int insert(carInformation carInformation) {
       int i=carInformationMapper.insert(carInformation);
       return i;
    }
}
