package com.example.demo.mq.consumer;

import com.example.demo.entity.breakRule;
import com.example.demo.service.breakRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class consumer {
    @Autowired
    @Qualifier("rmiBreakRuleService1")
    private breakRuleService breakRuleService;

    @JmsListener(destination = "beijin")
    public void receive(Integer message){
        System.out.println("接收到消息："+message);
        breakRule breakRule=new breakRule();
        breakRule.setId(message);
        breakRule.setPay("是");
        int i=breakRuleService.update(breakRule);
    }
}
