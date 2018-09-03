package com.zz.opensdk.web.controller;


import com.alibaba.fastjson.JSON;
import com.zz.opensdk.web.bean.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *  跨域
 */
@Controller
@RequestMapping("/cors")
public class ControllerCORS extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerCORS.class);

    @RequestMapping(value = "/testApi")
    @ResponseBody
    public String testApi(UserInfo userInfo) {
        LOGGER.info("userInfo="+JSON.toJSONString(userInfo));
        return JSON.toJSONString(userInfo);
    }

}
