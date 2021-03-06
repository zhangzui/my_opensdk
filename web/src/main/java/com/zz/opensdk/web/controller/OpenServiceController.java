package com.zz.opensdk.web.controller;


import com.alibaba.fastjson.JSON;
import com.alipay.jarslink.api.Module;
import com.alipay.jarslink.api.ModuleLoader;
import com.alipay.jarslink.api.ModuleManager;
import com.zz.opensdk.sdk.domain.OpenAPIEntity;
import com.zz.opensdk.service.TransactionService;
import com.zz.opensdk.web.bean.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author zhangzuizui
 */
@Controller
@RequestMapping("/open_api")
public class OpenServiceController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenServiceController.class);

    @Autowired
    private TransactionService thransactionService;

    @RequestMapping(value = "/gateway")
    @ResponseBody
    public OpenAPIEntity gateway(@RequestBody OpenAPIEntity openAPIEntity) {
        LOGGER.info("openAPIEntity----请求参数"+JSON.toJSONString(openAPIEntity));

        OpenAPIEntity result = thransactionService.transactionService(openAPIEntity);

        LOGGER.info("openAPIEntity----响应参数"+JSON.toJSONString(openAPIEntity));
        return result;
    }
}
