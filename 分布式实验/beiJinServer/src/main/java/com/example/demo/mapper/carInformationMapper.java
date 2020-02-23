package com.example.demo.mapper;

import com.example.demo.entity.carInformation;

import java.util.List;

public interface carInformationMapper {
    List<carInformation> queryAll();
    carInformation queryByAccount(String account);
    int insert(carInformation carInformation);
}
