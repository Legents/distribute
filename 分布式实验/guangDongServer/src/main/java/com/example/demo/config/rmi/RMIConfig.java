package com.example.demo.config.rmi;

import com.example.demo.service.breakRuleService;
import com.example.demo.service.carInformationService;
import com.example.demo.service.userService;
import com.example.demo.serviceImpl.breakRuleServiceImpl;
import com.example.demo.serviceImpl.userServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;

import java.rmi.RemoteException;

@Configuration
public class RMIConfig {
    @Autowired
    @Qualifier("rmiUserService1")
    private userServiceImpl userService;
    @Bean
    public RmiServiceExporter init() throws RemoteException {
        RmiServiceExporter exporter=new RmiServiceExporter();
        exporter.setServiceInterface(userService.class);
        exporter.setServiceName("rmiUserServiceGuangDong");
        exporter.setService(userService);
        exporter.setRegistryPort(1100);
       // exporter.setServicePort(1100);
        return exporter;
    }
    @Autowired
    @Qualifier("rmiCarService1")
    private carInformationService carService;
    @Bean
    public RmiServiceExporter init2(){
        RmiServiceExporter exporter=new RmiServiceExporter();
        exporter.setServiceInterface(carInformationService.class);
        exporter.setServiceName("rmiCarServiceGuangDong");
        exporter.setService(carService);
        exporter.setRegistryPort(2003);
        return exporter;
    }
    @Autowired
    @Qualifier("rmiBreakRuleService1")
    private breakRuleServiceImpl breakRuleService;
    @Bean
    public RmiServiceExporter init3(){
        RmiServiceExporter exporter=new RmiServiceExporter();
        exporter.setServiceInterface(com.example.demo.service.breakRuleService.class);
        exporter.setServiceName("rmiBreakRuleServiceGuangDong");
        exporter.setService(breakRuleService);
        exporter.setRegistryPort(3003);
        return exporter;
    }
}
