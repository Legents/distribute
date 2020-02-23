package com.example.demo.util;

import com.example.demo.entity.carInformation;
import com.example.demo.service.carInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class carUtil {
    @Autowired
    @Qualifier("rmiCarService1")
    private carInformationService carInformationService;
    @Autowired
    @Qualifier("rmiCarServiceClient")
    private RmiProxyFactoryBean factoryShangHai;
    @Autowired
    @Qualifier("rmiCarServiceClient2")
    private RmiProxyFactoryBean factoryGuangDong;

    private static carInformation carInformation;

    private static carUtil carUtil1;

    @PostConstruct
    private void init(){
        carUtil1=this;
    }

    public static Map<String,Object> queryByAccount(String account){
        Map<String,Object> map=new HashMap();
        Integer range=Integer.parseInt(""+account.subSequence(0,1));
        String msg=null;
        try {
            if (range >= 1 && range <= 3) {
                carInformationService carInformationService = (carInformationService) carUtil1.factoryShangHai.getObject();
                carInformation = carInformationService.queryByAccount(account);
            } else if (range >= 7 && range <= 9) {
                carInformationService carInformationService = (carInformationService) carUtil1.factoryGuangDong.getObject();
                carInformation = carInformationService.queryByAccount(account);
            } else if (range >= 4 && range <= 6) {
                carInformation = carUtil1.carInformationService.queryByAccount(account);
            }
        }catch(RuntimeException e){
            e.printStackTrace();
            msg="该账户车辆信息所在服务器异常";
        }
        map.put("carInformation",carInformation);
        map.put("msg",msg);
        return map;
    }
}
