package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class exitController {
    @GetMapping("/exit")
    public String exit(HttpSession session){
        session.setAttribute("accountLogin",null);
        return "login";
    }
}
