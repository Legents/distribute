package com.example.demo.mapper;

import com.example.demo.entity.breakRule;

import java.util.List;

public interface breakRuleMapper {
    List<breakRule> queryByCarNumber(String carNumber);
    int insert(breakRule breakRule);
    int update(breakRule breakRule);
}
