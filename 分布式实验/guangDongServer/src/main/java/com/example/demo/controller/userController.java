package com.example.demo.controller;

import com.example.demo.entity.user;
import com.example.demo.service.policeService;
import com.example.demo.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class userController {
    @Autowired
    private policeService policeService;


    @Autowired
    @Qualifier("rmiUserService1")
    private userService userService;
    @Autowired
    @Qualifier("rmiUserServiceClient")
    private RmiProxyFactoryBean factoryBean;
    @Autowired
    @Qualifier("rmiUserServiceClient2")
    private RmiProxyFactoryBean factoryBean2;
    @GetMapping("/queryall")
    @ResponseBody
    public Map<String,Object> queryAll(){
        Map<String,Object> map=new HashMap<>();
        List<user> userList=userService.queryAll();
        map.put("userList",userList);
        return map;
    }
    @PostMapping("/query")
    @ResponseBody
    public user query(String account){
        user user=userService.queryUserByAccount(account);
        if(user==null){
            System.out.println("客户端远程调用服务");
            userService userService1=(userService) factoryBean.getObject();
            user=userService1.queryUserByAccount(account);
        }
        if(user==null){
            System.out.println("客户端远程调用服务");
            userService userService1=(userService) factoryBean2.getObject();
            user=userService1.queryUserByAccount(account);
        }

        return user;
    }
    @RequestMapping("/login")
    public ModelAndView login(@RequestParam("account") String account,
                              @RequestParam("password") String password,
                              @RequestParam("type") String type,
                              HttpSession session){
        String msg=null;
        user user=null;
        Integer range=Integer.parseInt(""+account.subSequence(0,1));

        //System.out.println(type);
        ModelAndView mv=new ModelAndView();
        if(!StringUtils.isEmpty(account) &&!StringUtils.isEmpty(password)) {
            if(type.equals("车主")) {
                System.out.println("从车主查询");
                try {
                    if (range >= 1 && range <= 3) {
                        System.out.println("广东客户端调用上海远程服务");
                        userService userService1 = (userService) factoryBean.getObject();
                        user = userService1.queryUserByAccount(account);
                    } else if (range >= 4 && range <= 6) {
                        System.out.println("广东客户端调用北京远程服务");
                        userService userService1 = (userService) factoryBean2.getObject();
                        user = userService1.queryUserByAccount(account);
                    } else if (range >= 7 && range <= 9) {
                        user = userService.queryUserByAccount(account);
                    }
                }catch (RuntimeException e){
                    msg = "该用户所在服务器异常";
                    mv.setViewName("login");
                    mv.addObject("msg",msg);
                    return mv;
                }
            }else if(type.equals("警察")){
                System.out.println("从警察查询");
                user = policeService.queryUserByAccount(account);
            }
            System.out.println("user:"+user);
            if (user != null && user.getAccount().equals(account) && user.getPassword() == (Integer.parseInt(password))) {
                //登陆成功
                session.setAttribute("accountLogin", account);
                if(type.equals("车主")) {
                    mv.setViewName("main");
                }else if(type.equals("警察")){
                    mv.setViewName("police");
                }
                return mv;
            } else {
                //登陆失败
                msg = "用户名、密码不正确";
                mv.setViewName("login");

            }

        }else{
            msg="用户名，密码不能为空";
            mv.setViewName("login");
        }

        mv.addObject("msg",msg);
        return mv;
    }
    @GetMapping("/add")
    @ResponseBody
    public void add(){
        user user=new user();
        user.setAccount("222");
        user.setPassword(222);
        int i=userService.insertUser(user);
    }


}
