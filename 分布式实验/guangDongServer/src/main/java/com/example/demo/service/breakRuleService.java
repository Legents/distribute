package com.example.demo.service;

import com.example.demo.entity.breakRule;

import java.util.ArrayList;
import java.util.List;

public interface breakRuleService {
    List<breakRule> queryByCarNumber(String carNumber);
    int insert(breakRule breakRule);
    int update(breakRule breakRule);
}
