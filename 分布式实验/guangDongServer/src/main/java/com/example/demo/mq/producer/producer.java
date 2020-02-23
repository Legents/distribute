package com.example.demo.mq.producer;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;


@Component
public class producer {
    @Autowired
    private JmsMessagingTemplate jmsTemplate;
    public void sendMessage(String name, Integer message){

        Destination destination=new ActiveMQQueue(name);
        jmsTemplate.convertAndSend(destination,message);
        System.out.println("该服务器发送一条消息 To:"+name+"消息队列");
    }
}
