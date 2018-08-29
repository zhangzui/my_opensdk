package com.zz.opensdk.web.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhangzuizui on 2017/11/18.
 * 测试vm
 */
@Controller
@RequestMapping("/hello")
public class HelloWorldController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);

    @RequestMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        LOGGER.info("index");
        modelAndView.setViewName("vm/index");
        return modelAndView;
    }
}
