package com.zz.opensdk.web.controller;


import com.alibaba.fastjson.JSON;
import com.zz.opensdk.sdk.domain.OpenAPIEntity;
import com.zz.opensdk.web.bean.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author zhangzuizui
 * @RequestParam注解：
 * 用来处理Content-Type: 为 application/x-www-form-urlencoded编码的内容，提交方式GET、POST；
 *
 * @RequestBody注解:
 * 该注解常用来处理Content-Type: 不是application/x-www-form-urlencoded编码的内容，例如application/json, application/xml等；
 */
@Controller
@RequestMapping("/test")
public class ControllerTest extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerTest.class);

    @RequestMapping(value = "/gateway0")
    @ResponseBody
    public String gateway0(@RequestBody UserInfo userInfo) {
         LOGGER.info("userInfo="+JSON.toJSONString(userInfo));
         return JSON.toJSONString(userInfo);
    }

    @RequestMapping(value = "/gateway1")
    @ResponseBody
    public String gateway1(UserInfo userInfo) {
        LOGGER.info("userInfo="+JSON.toJSONString(userInfo));
        return JSON.toJSONString(userInfo);
    }

    @RequestMapping(value = "/gateway2")
    @ResponseBody
    public String gateway2(String name,String password) {
        LOGGER.info("name="+name+",password="+password);
        return "gateway2-result:name="+name+",password="+password;
    }

    @RequestMapping(value = "/gateway3")
    @ResponseBody
    public String gateway3(HttpServletRequest request,String name,String password) {
        LOGGER.info("gateway4,name="+name+",password="+password);
        return "gateway3-result:name="+name+",password="+password;
    }
    @RequestMapping(value = "/gateway4")
    @ResponseBody
    public String gateway4(HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        LOGGER.info("gateway4_1,name="+name+",password="+password);
        return "gateway4-result:name="+name+",password="+password;
    }

    @RequestMapping(value = "/gateway5")
    @ResponseBody
    public String gateway5(List<String> list) {
        LOGGER.info("gateway5,name="+list.get(0)+",password="+list.get(1));
        return "gateway5-result:name="+list.get(0)+",password="+list.get(1);
    }

    @RequestMapping(value = "/gateway6")
    @ResponseBody
    public String gateway6(@RequestBody List<String> list) {
        LOGGER.info("gateway6,name="+list.get(0)+",password="+list.get(1));
        return "gateway6-result:name="+list.get(0)+",password="+list.get(1);
    }

    /**
     * PathVariable 路径变量
     */
    @RequestMapping(value = "/gateway7/{userId}",params="userPwd=123456")
    @ResponseBody
    public String gateway7(UserInfo userInfo,@PathVariable String userId) {
         LOGGER.info("gateway7,userId="+userId);
         LOGGER.info("gateway7,userInfo="+JSON.toJSONString(userInfo));
        return "gateway7-success";
    }

    @RequestMapping(value = "/gateway8/{userId}",consumes="application/json", produces="application/json")
    @ResponseBody
    public String gateway8(@RequestBody List<String> list,@PathVariable String userId) {
        Map<String,String> map = new HashMap<>();
        map.put("name",list.get(0));
        map.put("pwd",list.get(1));
        map.put("userid",userId);
        LOGGER.info("gateway7,name="+list.get(0)+",password="+list.get(1)+",userid="+userId);
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/gateway9/{userId}",consumes="application/json", produces="application/json")
    @ResponseBody
    public String gateway9(List<String> list,@PathVariable String userId) {
        LOGGER.info("gateway7,name="+list.get(0)+",password="+list.get(1)+",userid="+userId);
        return "gateway9-success";
    }

    /**
     *  仅处理request的header中包含了指定“Refer”请求头和对应值为“http://www.ifeng.com/”的请求；
     *  headers="Referer=http://aaa.com:8080/html/test.html"
     */
    @RequestMapping(value = "/gateway10", method = RequestMethod.GET,headers="Referer=http://aaa.com:8080/html/test.html")
    @ResponseBody
    public String gateway10() {
        LOGGER.info("gateway10---test02=");
        return "gateway10-success";
    }

}
