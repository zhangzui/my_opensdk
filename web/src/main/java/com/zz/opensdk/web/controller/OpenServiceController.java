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
@RequestMapping("/open_api")
public class OpenServiceController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenServiceController.class);

    @RequestMapping(value = "/gateway")
    @ResponseBody
    public String gateway(@RequestBody UserInfo userInfo) {
        return JSON.toJSONString(userInfo);
    }

    @RequestMapping(value = "/gateway1")
    @ResponseBody
    public String gateway1(UserInfo userInfo) {
        return JSON.toJSONString(userInfo);
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
    public Object gateway7(UserInfo userInfo,@PathVariable String userId) {
        System.out.println("userId="+userId);
        System.out.println("userInfo="+JSON.toJSONString(userInfo));
        return "success";
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

}
