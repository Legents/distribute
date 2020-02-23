package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/police")
public class policeController {

    @RequestMapping("/login")
    public void login(@RequestParam("account") String account,
                              @RequestParam("password") String password, HttpSession session){

    }
}
