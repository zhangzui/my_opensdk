package com.zz.opensdk.web.controller;

import com.alibaba.fastjson.JSON;

import com.zz.opensdk.web.test.rpc.domain.MerchantkeyRes;
import com.zz.opensdk.sdk.common.exception.SystemException;
import com.zz.opensdk.sdk.domain.OpenAPIEntity;
import com.zz.opensdk.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzuizui
 * @date 2018/7/4 18:04
 */
@Controller
@RequestMapping("/utilService")
public class UtilController extends BaseController{
    private static final Logger LOGGER = LoggerFactory.getLogger(UtilController.class);

    @Autowired
    private TransactionService tradePayService;

    @RequestMapping(value = "/encode",method =  { RequestMethod.POST })
    @ResponseBody
    public OpenAPIEntity encode(OpenAPIEntity bizEntity) {
        return buildStrBizEntity(bizEntity);
    }

    @RequestMapping(value = "/decode",method =  { RequestMethod.POST })
    @ResponseBody
    public String decode(OpenAPIEntity bizEntity) {
        MerchantkeyRes merchantkeyRes = getMerchantSecreKeyBymerchantNo(bizEntity.getMerchantNo(),bizEntity.getAppId());
        String privateKey = merchantkeyRes.getAesPrivatekey();
        String saltKey = merchantkeyRes.getSalt();

        return "";
    }
    @RequestMapping(value = "/decodeAndDeSign",method =  { RequestMethod.POST })
    @ResponseBody
    public String decodeAndDeSign(OpenAPIEntity bizEntity) {
        return descriptData(bizEntity);
    }


    @RequestMapping(value = "/execute",method = { RequestMethod.POST })
    @ResponseBody
    public Map<String,Object> execute(OpenAPIEntity bizEntity) {
        LOGGER.info("提交bizEntity:{}",bizEntity);
        Map<String,Object> map = new HashMap<String,Object>();
        String resDecodeData;
        try {
            OpenAPIEntity req = buildStrBizEntity(bizEntity);
            LOGGER.info("组装bizEntity请求密文req:{}",req);
            OpenAPIEntity res = tradePayService.transactionService(req);
            LOGGER.info("响应密文res:{}",res);
            MerchantkeyRes merchantkeyRes = getMerchantSecreKeyBymerchantNo(bizEntity.getMerchantNo(),bizEntity.getAppId());
            String privateKey = merchantkeyRes.getAesPrivatekey();
            String saltKey = merchantkeyRes.getSalt();

            resDecodeData = "";
            map.put("req",req);
            map.put("res",res);
            map.put("resDecodeData",resDecodeData);
        } catch (SystemException e) {
            OpenAPIEntity resBizEntity = bizEntity;
            resBizEntity.setData("errorcode:"+e.getErrorCode() +",errorInfo:"+e.getShowMessage());
            map.put("req",bizEntity);
            map.put("res",resBizEntity);
            map.put("resDecodeData",JSON.toJSONString(resBizEntity));
        }catch (Exception e) {
            OpenAPIEntity resBizEntity = bizEntity;
            resBizEntity.setData("e.getMessage():"+e.getMessage());
            map.put("req",bizEntity);
            map.put("res",resBizEntity);
            map.put("resDecodeData",JSON.toJSONString(resBizEntity));
        }
        LOGGER.info("响应明文resDecodeData:{}", JSON.toJSONString(map));
        return map;
    }
}
