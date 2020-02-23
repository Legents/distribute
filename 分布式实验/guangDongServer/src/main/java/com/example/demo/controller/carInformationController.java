package com.example.demo.controller;

import com.example.demo.entity.carInformation;
import com.example.demo.entity.user;
import com.example.demo.service.carInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/car")
public class carInformationController {

    @Autowired
    @Qualifier("rmiCarService1")
    private carInformationService carInformationService;
    @Autowired
    @Qualifier("rmiCarServiceClient2")
    private RmiProxyFactoryBean factoryBeiJin;

    @Autowired
    @Qualifier("rmiCarServiceClient")
    private RmiProxyFactoryBean factoryShangHai;

    @GetMapping("/queryall")
    @ResponseBody
    public Map<String,Object> queryAll(){
        Map<String,Object> map=new HashMap<>();
        List<carInformation> carList=carInformationService.queryAll();
        map.put("carList",carList);
        return map;
    }

    @GetMapping("/query")
    public ModelAndView query(String account){
        Integer range=Integer.parseInt(""+account.subSequence(0,1));
       // System.out.println("账户首位数字："+range);
        ModelAndView mv=new ModelAndView();
        carInformation carInformation=null;
        String msg=null;
        try{
       if(range>=1&&range<=3){
           carInformationService carInformationService=(carInformationService) factoryShangHai.getObject();
           carInformation=carInformationService.queryByAccount(account);
       }else if(range>=4&&range<=6){
            carInformationService carInformationService=(carInformationService) factoryBeiJin.getObject();
            carInformation=carInformationService.queryByAccount(account);
        }else if(range>=7&&range<=9){
           carInformation=carInformationService.queryByAccount(account);
       }
        }catch(RuntimeException e){
            msg="该账户车辆信息所在服务器异常";
        }
        mv.addObject("msg",msg);
        mv.addObject("car",carInformation);
        mv.setViewName("information");
        return mv;
    }
    @GetMapping("/add")
    @ResponseBody
    public int add(){
        carInformation car=new carInformation();
        car.setAccount("222");
        car.setCarNumber("沪B 222");
        car.setUserName("杨二");
        car.setPhone(222);
        int i=carInformationService.insert(car);
        return i;
    }


}
