package com.example.demo.service;

import com.example.demo.entity.carInformation;

import java.util.List;

public interface carInformationService {
    List<carInformation> queryAll();
    carInformation queryByAccount(String account);
    int insert(carInformation carInformation);
}
