package com.zz.opensdk.web.controller;


import com.alibaba.fastjson.JSON;
import com.alipay.jarslink.api.Module;
import com.alipay.jarslink.api.ModuleLoader;
import com.alipay.jarslink.api.ModuleManager;
import com.zz.opensdk.sdk.domain.OpenAPIEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author zhangzuizui
 */
@Controller
@RequestMapping("/jarslink")
public class JarsLinkOpenServiceController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(JarsLinkOpenServiceController.class);

    @Autowired
    private ModuleManager moduleManager;

    @Autowired
    private ModuleLoader moduleLoader;

    @RequestMapping(value = "/gateway")
    @ResponseBody
    public OpenAPIEntity gateway(@RequestBody OpenAPIEntity openAPIEntity) {
        LOGGER.info("openAPIEntity----请求参数"+JSON.toJSONString(openAPIEntity));

        Module module = moduleManager.find("opensdk",openAPIEntity.getVersion());
        OpenAPIEntity result  = module.doAction("opensdk",openAPIEntity);

        LOGGER.info("openAPIEntity----响应参数"+JSON.toJSONString(openAPIEntity));


        return result;
    }
}
