package com.qlone.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qlone.api.login.Test;
import com.qlone.api.login.service.UserModelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class TestController {
    @Reference(version = "1.0.0")
    private Test test;
    @Reference(version = "1.0.0")
    private UserModelService modelService;

    @ResponseBody
    @RequestMapping("")
    public Object getBill(ModelAndView modelAndView){
        modelAndView.addObject("res",test.getMessage());
        return modelAndView.getModel();
    }

    @ResponseBody
    @RequestMapping("/s")
    public Object newUser(ModelAndView modelAndView){
        String res = modelService.loginAndGetToken("test","password");

        modelAndView.addObject("res",res);
        return modelAndView.getModel();
    }

    @ResponseBody
    @RequestMapping("/c")
    public Object checkToken(ModelAndView modelAndView, @PathVariable String token){
        String res = modelService.checkToken(token);

        modelAndView.addObject("res",res);
        return modelAndView.getModel();
    }
}
