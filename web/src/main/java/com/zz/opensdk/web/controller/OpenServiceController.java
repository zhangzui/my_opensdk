package com.zz.opensdk.web.controller;


import com.alibaba.fastjson.JSON;
import com.zz.opensdk.sdk.common.config.ApiTypeEnum;
import com.zz.opensdk.sdk.common.config.ExceptionConstants;
import com.zz.opensdk.sdk.common.exception.SystemExceptionAdapter;
import com.zz.opensdk.sdk.domain.OpenAPIEntity;
import com.zz.opensdk.service.TransactionService;
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
 *
 * 外部网关服务
 */
@Controller
@RequestMapping("/open_api")
public class OpenServiceController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenServiceController.class);

    @Autowired
    private TransactionService transactionService;


    @RequestMapping(value = "/gateway")
    @ResponseBody
    public OpenAPIEntity gateway(@RequestBody OpenAPIEntity openAPIEntity) {
        return transactionService.transactionService(openAPIEntity);
    }

    @RequestMapping(value = "/gateway1")
    @ResponseBody
    public OpenAPIEntity execute1(OpenAPIEntity openAPIEntity) {
        return transactionService.transactionService(openAPIEntity);
    }

    @RequestMapping(value = "/gateway2.json")
    @ResponseBody
    public OpenAPIEntity gateway2(HttpServletRequest request) {
        OpenAPIEntity openAPIEntity = getOpenAPIEntity(request);
        LOGGER.info("交易类型[{}]统一调用服务开始,请求参数为:{}", ApiTypeEnum.get(openAPIEntity.getApiType()).getName(), JSON.toJSONString(openAPIEntity));
        return transactionService.transactionService(openAPIEntity);
    }

    @RequestMapping(value = "/gateway3")
    @ResponseBody
    public Object gateway3(String name,String password) {

        return "gateway3-result:name="+name+",password="+password;
    }

    @RequestMapping(value = "/gateway4")
    @ResponseBody
    public Object gateway4(HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        return "gateway4-result:name="+name+",password="+password;
    }

    @RequestMapping(value = "/gateway5")
    @ResponseBody
    public Object gateway5(List<String> list) {
        return "gateway4-result:name="+list.get(0)+",password="+list.get(1);
    }
    @RequestMapping(value = "/gateway6")
    @ResponseBody
    public Object gateway6(@RequestBody List<String> list) {
        return "gateway4-result:name="+list.get(0)+",password="+list.get(1);
    }

    /**
     * PathVariable 路径变量
     */
    @RequestMapping(value = "/gateway7/{userId}",method=RequestMethod.GET)
    @ResponseBody
    public Object gateway7(String name,String password,@PathVariable String userId) {


        return "gateway3-result:name="+name+",password="+password+",userId="+userId;
    }

    @RequestMapping(value = "/gateway7/{userId}",method=RequestMethod.POST,consumes="application/json", produces="application/json")
    @ResponseBody
    public Object gateway7(@RequestBody List<String> list,@PathVariable String userId) {
        Map<String,String> map = new HashMap<>();
        map.put("name",list.get(0));
        map.put("pwd",list.get(1));
        map.put("userid",userId);
        return map;
    }

    @RequestMapping(value = "/gateway7_1/",method=RequestMethod.POST,consumes="application/json", produces="application/json")
    @ResponseBody
    public Object gateway7_1(@RequestBody List<String> list,Model model) {
        Map<String,String> map = new HashMap<>();
        map.put("name",list.get(0));
        map.put("pwd",list.get(1));
        model.addAttribute("result",map);
        return model;
    }
    @RequestMapping(value = "/gateway8/{userId}",method=RequestMethod.POST)
    @ResponseBody
    public Object gateway8(String name,String password,@PathVariable String userId) {
        return "gateway3-result:name="+name+",password="+password+",id="+userId;
    }

    /**
     * 仅处理请求中包含了名为“myParam”，值为“myValue”的请求；
     */
    @RequestMapping(value = "/test01/{petId}", method = RequestMethod.GET, params="myParam=myValue")
    public void test01(@PathVariable String ownerId, @PathVariable String petId, Model model) {
        System.out.println("ownerId="+ownerId+",petId="+petId);
    }

    /**
     *  仅处理request的header中包含了指定“Refer”请求头和对应值为“http://www.ifeng.com/”的请求；
     */
    @RequestMapping(value = "/test02", method = RequestMethod.GET, headers="Referer=http://www.ifeng.com/")
    public void test02() {
        System.out.println("test02=");
    }


    private OpenAPIEntity getOpenAPIEntity(HttpServletRequest request) {
        String version = request.getParameter("version");
        String appId = request.getParameter("appId");
        String timestamp = request.getParameter("timestamp");
        String charset = request.getParameter("charset");
        String tradeType = request.getParameter("tradeType");
        String data = request.getParameter("data");
        String merchantNo = request.getParameter("merchantNo");
        String sign = request.getParameter("sign");
        String encryptType = request.getParameter("encryptType");
        String signType = request.getParameter("signType");

        //构造参数
        OpenAPIEntity openAPIEntity = new OpenAPIEntity();
        if (!org.springframework.util.StringUtils.hasText(version)){
            throw SystemExceptionAdapter.initFrontendExceptionInfo(ExceptionConstants.front_service_000002,"");
        }else {
            openAPIEntity.setVersion(version);
        }

        if (!org.springframework.util.StringUtils.hasText(appId)){
            throw SystemExceptionAdapter.initFrontendExceptionInfo(ExceptionConstants.front_service_000003,"");
        }else {
            openAPIEntity.setAppId(appId);
        }

        if (!org.springframework.util.StringUtils.hasText(timestamp)){
            throw SystemExceptionAdapter.initFrontendExceptionInfo(ExceptionConstants.front_service_000004,"");
        }else {
            openAPIEntity.setTimestamp(timestamp);
        }

        if (!org.springframework.util.StringUtils.hasText(charset)){
            throw SystemExceptionAdapter.initFrontendExceptionInfo(ExceptionConstants.front_service_000005,"");
        }else {
            openAPIEntity.setCharset(charset);
        }

        if (!org.springframework.util.StringUtils.hasText(tradeType)){
            throw SystemExceptionAdapter.initFrontendExceptionInfo(ExceptionConstants.front_service_000006,"");
        }else {
            openAPIEntity.setApiType(tradeType);
        }

        if (!org.springframework.util.StringUtils.hasText(merchantNo)){
            throw SystemExceptionAdapter.initFrontendExceptionInfo(ExceptionConstants.front_service_000006,"");
        }else {
            openAPIEntity.setMerchantNo(merchantNo);
        }

        if (!org.springframework.util.StringUtils.hasText(data)){
            throw SystemExceptionAdapter.initFrontendExceptionInfo(ExceptionConstants.front_service_000006,"");
        }else {
            openAPIEntity.setData(data);
        }

        if (!org.springframework.util.StringUtils.hasText(signType)){
            throw SystemExceptionAdapter.initFrontendExceptionInfo(ExceptionConstants.front_service_000006,"");
        }else {
            openAPIEntity.setSignType(signType);
        }
        if (!org.springframework.util.StringUtils.hasText(encryptType)){
            throw SystemExceptionAdapter.initFrontendExceptionInfo(ExceptionConstants.front_service_000006,"");
        }else {
            openAPIEntity.setEncryptType(encryptType);
        }
        if (!org.springframework.util.StringUtils.hasText(sign)){
            throw SystemExceptionAdapter.initFrontendExceptionInfo(ExceptionConstants.front_service_000006,"");
        }else {
            openAPIEntity.setSign(sign);
        }
        return openAPIEntity;
    }

}
