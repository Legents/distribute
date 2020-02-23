package com.example.demo.config.rmi;

import com.example.demo.service.breakRuleService;
import com.example.demo.service.carInformationService;
import com.example.demo.service.userService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@Configuration
public class RMIClientConfig {
    @Bean(name="rmiUserServiceClient")
    public RmiProxyFactoryBean init(){
        RmiProxyFactoryBean factoryBean=new RmiProxyFactoryBean();
        factoryBean.setServiceUrl("rmi://localhost:1099/rmiUserServiceShangHai");
        factoryBean.setServiceInterface(userService.class);
        factoryBean.setLookupStubOnStartup(false);//不在容器启动的时候创建与Server端的连接
        factoryBean.setRefreshStubOnConnectFailure(true);//表示是否连接出错时自动重连

        return factoryBean;
    }

    @Bean(name = "rmiUserServiceClient2")
    public RmiProxyFactoryBean init2(){
        RmiProxyFactoryBean factoryBean=new RmiProxyFactoryBean();
        factoryBean.setServiceUrl("rmi://localhost:1101/rmiUserServiceBeiJin");
        factoryBean.setServiceInterface(userService.class);
        factoryBean.setLookupStubOnStartup(false);//不在容器启动的时候创建与Server端的连接
        factoryBean.setRefreshStubOnConnectFailure(true);//表示是否连接出错时自动重连

        return factoryBean;
    }

    @Bean(name = "rmiCarServiceClient")
    public RmiProxyFactoryBean init3(){
        RmiProxyFactoryBean factoryBean=new RmiProxyFactoryBean();
        factoryBean.setServiceUrl("rmi://localhost:2001/rmiCarServiceShangHai");
        factoryBean.setServiceInterface(carInformationService.class);
        factoryBean.setLookupStubOnStartup(false);//不在容器启动的时候创建与Server端的连接
        factoryBean.setRefreshStubOnConnectFailure(true);//表示是否连接出错时自动重连

        return factoryBean;
    }

    @Bean(name="rmiCarServiceClient2")
    public RmiProxyFactoryBean init4(){
        RmiProxyFactoryBean factoryBean=new RmiProxyFactoryBean();
        factoryBean.setServiceUrl("rmi://localhost:2002/rmiCarServiceBeiJin");
        factoryBean.setServiceInterface(carInformationService.class);
        factoryBean.setLookupStubOnStartup(false);//不在容器启动的时候创建与Server端的连接
        factoryBean.setRefreshStubOnConnectFailure(true);//表示是否连接出错时自动重连

        return factoryBean;
    }
    @Bean(name="rmiBreakRuleServiceClient")
    public RmiProxyFactoryBean init5(){
        RmiProxyFactoryBean factoryBean=new RmiProxyFactoryBean();
        factoryBean.setServiceUrl("rmi://localhost:3001/rmiBreakRuleServiceShangHai");
        factoryBean.setServiceInterface(breakRuleService.class);
        factoryBean.setLookupStubOnStartup(false);//不在容器启动的时候创建与Server端的连接
        factoryBean.setRefreshStubOnConnectFailure(true);//表示是否连接出错时自动重连

        return factoryBean;
    }
    @Bean(name="rmiBreakRuleServiceClient2")
    public RmiProxyFactoryBean init6(){
        RmiProxyFactoryBean factoryBean=new RmiProxyFactoryBean();
        factoryBean.setServiceUrl("rmi://localhost:3002/rmiBreakRuleServiceBeiJin");
        factoryBean.setServiceInterface(breakRuleService.class);
        factoryBean.setLookupStubOnStartup(false);//不在容器启动的时候创建与Server端的连接
        factoryBean.setRefreshStubOnConnectFailure(true);//表示是否连接出错时自动重连

        return factoryBean;
    }



}
