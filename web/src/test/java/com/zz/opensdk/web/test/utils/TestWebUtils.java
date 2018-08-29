package com.zz.opensdk.web.test.utils;

import com.zz.opensdk.sdk.common.OpenApiConstants;
import com.zz.opensdk.sdk.utils.WebUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzuizui
 * @date 2018/7/12 15:36
 */
public class TestWebUtils {
    @Test
    public void testDoPost(){
        Map<String,String> parmasMap = buildMap();
        String url = "http://localhost:8080/openApi/gateway4.json";
        try {
            String rsp = WebUtils.doPost(url, parmasMap, "UTF-8",
                    3000, 10000, null, 0);
            System.out.println("rsp:"+rsp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDoPost2(){
        Map<String,String> parmasMap = buildMap();
        String url = "http://localhost:8080/openApi/gateway1";
        try {
            String rsp = WebUtils.doPost(url, parmasMap, "UTF-8",
                    3000, 10000, null, 0);
            System.out.println("rsp:"+rsp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String,String> buildMap(){
        Map<String,String> parmasMap = new HashMap<String, String>();
        parmasMap.put(OpenApiConstants.VERSION,"1.0.0");
        parmasMap.put(OpenApiConstants.APP_ID,"000001");
        parmasMap.put(OpenApiConstants.TIMESTAMP,"201807121538");
        parmasMap.put(OpenApiConstants.TRADE_TYPE,"00");
        parmasMap.put(OpenApiConstants.MERCHANTNO,"360087641000000005");
        parmasMap.put(OpenApiConstants.SIGN_TYPE,"RSA2");
        parmasMap.put(OpenApiConstants.ENCRYPT_TYPE,"AsES");
        parmasMap.put(OpenApiConstants.CHARSET,"utf-8");
        parmasMap.put(OpenApiConstants.DATA,"bodytest");
        parmasMap.put(OpenApiConstants.SIGN,"signtest");
        return parmasMap;
    }
}
