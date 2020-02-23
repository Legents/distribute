package com.example.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyInterceptor1 implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        //System.out.println("MyInterceptor1>>>preHandle");
        Object account=request.getSession().getAttribute("accountLogin");
        //System.out.println(account);
        if(account==null){
            request.setAttribute("msg","没有权限");
            try {
                request.getRequestDispatcher("/login.html").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }else{
            return true;
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
      //  System.out.println("MyInterceptor1>>>postHandle");

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
       // System.out.println("MyInterceptor1>>>afterCompletion");
    }
}