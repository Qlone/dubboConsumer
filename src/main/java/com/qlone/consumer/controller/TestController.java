package com.qlone.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qlone.api.basic.enumapi.ApiResult;
import com.qlone.api.login.Test;
import com.qlone.api.login.service.UserModelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class TestController {
    @Reference(version = "1.0.0")
    private Test test;
    @Reference(version = "1.0.0")
    private UserModelService userModelService;

    @ResponseBody
    @RequestMapping("/")
    public Object getBill(ModelAndView modelAndView){
        modelAndView.addObject("res",test.getMessage());
        return modelAndView.getModel();
    }

    @ResponseBody
    @RequestMapping("/s")
    public Object newUser(ModelAndView modelAndView,@RequestParam(value = "user") String user,
                          @RequestParam(value = "psw") String psw){
        String res = userModelService.loginAndGetToken(user,psw,false);

        modelAndView.addObject("res",res);
        return modelAndView.getModel();
    }

    @ResponseBody
    @RequestMapping("/c")
    public Object checkToken(ModelAndView modelAndView ,@RequestParam(value = "token") String token){
        String res = userModelService.checkToken(token);

        modelAndView.addObject("res",res);
        return modelAndView.getModel();
    }

    @ResponseBody
    @RequestMapping("/r")
    public Object register(ModelAndView modelAndView ,@RequestParam(value = "user") String user,
                           @RequestParam(value = "psw") String psw){
        ApiResult res = userModelService.registerAccount(user,psw);

        modelAndView.addObject("res",res);
        return modelAndView.getModel();
    }
}
