package com.zz.opensdk.web.controller;

import com.alibaba.fastjson.JSON;
import com.zz.opensdk.web.test.rpc.MerchantServiceRpc;
import com.zz.opensdk.web.test.rpc.domain.MerchantkeyRes;
import com.zz.opensdk.sdk.common.config.ApiTypeEnum;
import com.zz.opensdk.sdk.common.config.ExceptionConstants;
import com.zz.opensdk.sdk.common.exception.SystemException;
import com.zz.opensdk.sdk.common.exception.SystemExceptionAdapter;
import com.zz.opensdk.sdk.domain.OpenAPIEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangzuizui
 * @date 2018/2/28 14:11
 */
public class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private MerchantServiceRpc merchantServiceRpc;


    public String descriptData(OpenAPIEntity bizEntity) {
        MerchantkeyRes merchantkeyRes = getMerchantSecreKeyBymerchantNo(bizEntity.getMerchantNo(),bizEntity.getAppId());
        String privateKey = merchantkeyRes.getAesPrivatekey();
        String saltKey = merchantkeyRes.getSalt();
        LOGGER.info("交易类型[{}]解析BizEntity开始,descriptData请求参数为:{}", ApiTypeEnum.get(bizEntity.getApiType()).getName(), JSON.toJSONString(bizEntity));
        //3.data解密
        String dataSign =  "";
        LOGGER.info("交易类型[{}]解析BizEntity结束,descriptData返回参数为:{}", ApiTypeEnum.get(bizEntity.getApiType()).getName(), dataSign);
        return dataSign;
    }

    /**
     * 获取商家信息
     * @param merchantNo
     * @return
     */
    protected MerchantkeyRes getMerchantSecreKeyBymerchantNo(String  merchantNo, String  appid) {
        return merchantServiceRpc.queryMerchantKey(merchantNo,appid);
    }

    public OpenAPIEntity buildStrBizEntity(OpenAPIEntity bizEntity){
        OpenAPIEntity reqBizEntity = new OpenAPIEntity();
        try{
            LOGGER.info("交易类型[{}]组装BizEntity开始,请求参数为:{}", ApiTypeEnum.get(bizEntity.getApiType()).getName(),bizEntity.getData());
            //获取商家配置
            MerchantkeyRes merchantkeyRes = getMerchantSecreKeyBymerchantNo(bizEntity.getMerchantNo(),bizEntity.getAppId());
            String privateKey = merchantkeyRes.getAesPrivatekey();
            String saltKey = merchantkeyRes.getSalt();
            //5.封装返回
            reqBizEntity.setVersion(bizEntity.getVersion());
            reqBizEntity.setCharset(bizEntity.getCharset());
            reqBizEntity.setApiType(bizEntity.getApiType());
            reqBizEntity.setMerchantNo(bizEntity.getMerchantNo());
            reqBizEntity.setAppId(bizEntity.getAppId());
            //6.data加密

            //7.生成带盐值的sign验签

            LOGGER.info("交易类型[{}]组装BizEntity结束,返回参数为:{}", ApiTypeEnum.get(bizEntity.getApiType()).getName(), JSON.toJSONString(bizEntity));
        }catch (SystemException e){
            LOGGER.error("组装BizEntity-SystemException",e);
            throw e;
        }catch (Exception e){
            LOGGER.error("组装BizEntity其他异常",e);
            throw SystemExceptionAdapter.initFrontendExceptionInfo(ExceptionConstants.front_service_000001);
        }
        return reqBizEntity;
    }
}
