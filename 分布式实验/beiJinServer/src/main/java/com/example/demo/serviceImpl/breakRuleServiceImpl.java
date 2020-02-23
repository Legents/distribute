package com.example.demo.serviceImpl;

import com.example.demo.entity.breakRule;
import com.example.demo.mapper.breakRuleMapper;
import com.example.demo.service.breakRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value="rmiBreakRuleService1")
public class breakRuleServiceImpl implements breakRuleService {
    @Autowired
    private breakRuleMapper breakRuleMapper;

    @Override
    @Cacheable(value="c222",key="222")
    public List<breakRule> queryByCarNumber(String carNumber) {
        System.out.println("从北京服务器查询违规信息");
        return breakRuleMapper.queryByCarNumber(carNumber);
    }

    @Transactional
    @Override
    @CachePut(value="c222")
    public int insert(breakRule breakRule) {
        System.out.println("北京服务器插入了新的违章信息");
        int i=breakRuleMapper.insert(breakRule);
        return i;
    }
    @Transactional
    @CacheEvict(value="c222",key="222")
    public int update(breakRule breakRule){
        System.out.println("北京服务器的违章信息被更新");
        int i=breakRuleMapper.update(breakRule);
        return i;
    }
}
