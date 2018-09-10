package com.zz.opensdk.web.controller;


import com.alibaba.fastjson.JSON;
import com.zz.opensdk.web.bean.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.spi.http.HttpContext;
import java.io.IOException;


@Controller
@RequestMapping("/cors")
public class ControllerCORS{
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerCORS.class);

    @RequestMapping(value = "/api0")
    @ResponseBody
    public String api0(UserInfo userInfo,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        LOGGER.info("userInfo="+JSON.toJSONString(userInfo));
        return JSON.toJSONString(userInfo);
    }

    @RequestMapping(value = "/api00")
    @ResponseBody
    @CrossOrigin
    public void api00(UserInfo userInfo,HttpServletRequest request,HttpServletResponse response) throws IOException {
        LOGGER.info("userInfo="+JSON.toJSONString(userInfo));
        String jsonp = "[" + JSON.toJSONString(userInfo) +"]";
        response.setContentType("text/plain");
        //得到js函数名称
        String callbackFunName = request.getParameter("callbackparam");

        response.getWriter().write(callbackFunName+"(["+jsonp+"])");
    }

    /**
     *  @CrossOrigin注解需要指定请求方式。get|post
     * @param userInfo
     * @param response
     * @return
     */
    @RequestMapping(value = "/api01",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public String api01(UserInfo userInfo,HttpServletResponse response) {
        LOGGER.info("userInfo="+JSON.toJSONString(userInfo));
        String result = JSON.toJSONString(userInfo);
        return result;
    }
    /**
     * 返回json变成返回js了，所以服务器是要改动支持的，不是调用方一厢情愿说用就能用的。
     * 由于是动态内嵌script标签，那么肯定是不支持post方法了
     * @return
     */
    @RequestMapping(value = "/api1")
    @ResponseBody
    public Object api1(HttpServletResponse response) {

        UserInfo userInfo = buildUserInfo();
        response.setHeader("Access-Control-Allow-Origin", "*");
        LOGGER.info("userInfo="+JSON.toJSONString(userInfo));
        return JSON.toJSONString(userInfo);
    }

    @RequestMapping(value = "/api2")
    @ResponseBody
    public Object api2(HttpServletRequest request,HttpServletResponse response) {
        UserInfo userInfo = buildUserInfo();
        response.setHeader("Access-Control-Allow-Origin", "*");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userInfo);
        System.out.println("api2-----"+request.getParameter("callbackparam"));
        mappingJacksonValue.setJsonpFunction(request.getParameter("callbackparam"));
        //得到js函数名称
        return mappingJacksonValue;
    }


    /**
     * document.cookie上增加
     * 服务器设置 @CookieValue(required=false) 即可
     * @param cookie
     * @return
     */
    @RequestMapping("api4")
    @ResponseBody
    public UserInfo api4(@CookieValue(required=false) String cookie) {
        UserInfo userInfo = buildUserInfo();
        System.out.println("\n-------ControllerCORS.api3(), cookie="+cookie);
        return userInfo;
    }

    /**
     * 带自定义header的跨域请求
     *
     * 注意，@CrossOrigin(allowedHeaders = { "X-Custom-Header1", "X-Custom-Header2", "X-Custom-Header4" })需要配置在方法上，
     * 不要配在类上面的 @CrossOrigin 注解上，否则会导致一些问题。
     * @return
     */
    @RequestMapping("api5")
    @ResponseBody
    public UserInfo api5(@RequestHeader(required = false, name = "X-Custom-Header") String header) {
        UserInfo userInfo = buildUserInfo();
        System.out.println("\n-------ControllerCORS.api4(), header=" + header);
        return userInfo;
    }

    public UserInfo buildUserInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("12121");
        userInfo.setUserName("zzz");
        userInfo.setUserPwd("123456");
        return userInfo;
    }
}
