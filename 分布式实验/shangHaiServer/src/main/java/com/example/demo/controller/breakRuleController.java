package com.example.demo.controller;

import com.example.demo.entity.breakRule;
import com.example.demo.entity.carInformation;
import com.example.demo.mq.producer.producer;
import com.example.demo.service.breakRuleService;
import com.example.demo.service.carInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.plugin2.message.Message;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/breakrule")
public class breakRuleController {
    @Autowired
    @Qualifier("rmiBreakRuleService1")
    private breakRuleService breakRuleService;
    @Autowired
    @Qualifier("rmiBreakRuleServiceClient")
    private RmiProxyFactoryBean factoryBeanBeiJin;
    @Autowired
    @Qualifier("rmiBreakRuleServiceClient2")
    private RmiProxyFactoryBean factoryBeanGuangDong;

    @Autowired
    @Qualifier("rmiCarService1")
    private carInformationService carInformationService;
    @Autowired
    @Qualifier("rmiCarServiceClient")
    private RmiProxyFactoryBean factoryBeiJin;
    @Autowired
    @Qualifier("rmiCarServiceClient2")
    private RmiProxyFactoryBean factoryGuangDong;




    @GetMapping("/query")
    public ModelAndView query( HttpSession session){
        String account=(String)session.getAttribute("accountLogin");

        Integer range=Integer.parseInt(""+account.subSequence(0,1));
        //System.out.println("账户首位数字："+range);
        ModelAndView mv=new ModelAndView();
        carInformation carInformation=null;
        try {
            if (range >= 4 && range <= 6) {
                carInformationService carInformationService = (carInformationService) factoryBeiJin.getObject();
                carInformation = carInformationService.queryByAccount(account);
            } else if (range >= 7 && range <= 9) {
                carInformationService carInformationService = (carInformationService) factoryGuangDong.getObject();
                carInformation = carInformationService.queryByAccount(account);
            } else if (range >= 1 && range <= 3) {
                carInformation = carInformationService.queryByAccount(account);
            }
        }catch(RuntimeException e){

        }

           List<breakRule> breakRules=new ArrayList<>();

        if(carInformation!=null) {
            breakRules = breakRuleService.queryByCarNumber(carInformation.getCarNumber());

            try {
                System.out.println("调用北京服务器");
                breakRuleService breakRuleService1 = (breakRuleService) factoryBeanBeiJin.getObject();
                List<breakRule> breakRules1 = breakRuleService1.queryByCarNumber(carInformation.getCarNumber());
                breakRules.addAll(breakRules1);
            } catch (RuntimeException e) {

            }

            try {
                System.out.println("调用广东服务器");
                breakRuleService breakRuleService2 = (breakRuleService) factoryBeanGuangDong.getObject();
                List<breakRule> breakRules2 = breakRuleService2.queryByCarNumber(carInformation.getCarNumber());
                breakRules.addAll(breakRules2);
            } catch (RuntimeException e) {

            }
        }
        mv.setViewName("breakRule");
        mv.addObject("breakRules",breakRules);
        return mv;
    }

    @Autowired
    private producer producer;

    @GetMapping("/update")
    @ResponseBody
    public int update(Integer id,String city ){

        int i=0;
        breakRule breakRule=new breakRule();
        breakRule.setId(id);
        breakRule.setPay("是");
        //交完罚款后更新违章信息
        if(city.equals("北京")){
            producer.sendMessage("beijin",id);

        }else if(city.equals("广东")){
            producer.sendMessage("guangdong",id);
        }else{
             i=breakRuleService.update(breakRule);
        }
        System.out.println(i);

        return 1;
    }

    @PostMapping("/add")
    public ModelAndView add(@RequestParam("carNumber") String carNumber,
                            @RequestParam("carInformation") String carInformation,
                            @RequestParam("penalty") String penalty
                            ){
        ModelAndView mv=new ModelAndView();
        String msg=null;
        breakRule breakRule=new breakRule();
        breakRule.setCarNumber(carNumber);
        breakRule.setInformation(carInformation);
        breakRule.setPenalty(Integer.parseInt(penalty));
        int i=breakRuleService.insert(breakRule);
        if(i>0){
            msg="添加成功";
        }
        mv.setViewName("police");
        mv.addObject("msg1",msg);
        return mv;
    }
}
