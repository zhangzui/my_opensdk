package com.zz.opensdk.service;

import com.alibaba.fastjson.JSON;
import com.zz.opensdk.web.test.rpc.MerchantServiceRpc;
import com.zz.opensdk.sdk.common.OpenApiConstants;
import com.zz.opensdk.sdk.common.OpenApiRuntimeException;
import com.zz.opensdk.sdk.common.config.ApiTypeEnum;
import com.zz.opensdk.sdk.common.config.ExceptionConstants;
import com.zz.opensdk.sdk.common.exception.FrontEndSysException;
import com.zz.opensdk.sdk.common.util.GsonUtils;
import com.zz.opensdk.web.test.rpc.domain.MerchantkeyRes;
import com.zz.opensdk.sdk.domain.OpenAPIEntity;
import com.zz.opensdk.sdk.domain.ResponseBaseVo;
import com.zz.opensdk.sdk.utils.EncryptUtils;
import com.zz.opensdk.sdk.utils.OpenApiHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import com.zz.opensdk.sdk.common.exception.SystemExceptionAdapter;

/**
 * @author zhangzuizui
 */
public abstract  class OpenBaseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenBaseService.class);


    private static final String SIGNTYPE = OpenApiConstants.SIGN_TYPE_RSA2;
    private static final String ENCRYPTTYPE = OpenApiConstants.ENCRYPT_TYPE_AES;

    @Autowired
    private MerchantServiceRpc merchantServiceRpc;

    public String openAPIDescriptData(OpenAPIEntity openAPIEntity) throws OpenApiRuntimeException {
        LOGGER.info("交易类型[{}]解析BizEntity开始,descriptData请求参数为:{}", ApiTypeEnum.get(openAPIEntity.getApiType()).getName(), GsonUtils.toJson(openAPIEntity));
        String data = null;
        MerchantkeyRes merchantkeyRes = getMerchantSecreKeyBymerchantNo(openAPIEntity.getMerchantNo(), openAPIEntity.getAppId());
        String merPublicKey = merchantkeyRes.getMerPublicKey();
        String aesPrivateKey = merchantkeyRes.getAesPrivatekey();

        //验签
        boolean checkSignRes = OpenApiHelper.signCheck(openAPIEntity, merPublicKey, SIGNTYPE);
        if(!checkSignRes){
            throw SystemExceptionAdapter.initFrontendExceptionInfo(ExceptionConstants.front_service_000005);
        }
        //解密
        if(ENCRYPTTYPE.equals(openAPIEntity.getEncryptType())){
            data = EncryptUtils.decryptContent(openAPIEntity.getData(), "AES",
                    aesPrivateKey, openAPIEntity.getCharset());
        }else {
            data = openAPIEntity.getData();
        }
        LOGGER.info("交易类型[{}]解析BizEntity结束,descriptData返回参数为:{}", ApiTypeEnum.get(openAPIEntity.getApiType()).getName(), data);
        return data;
    }

    public OpenAPIEntity buildErrorResponse(OpenAPIEntity openAPIEntity, String errorCode, String errorInfo) {
       ResponseBaseVo result = new ResponseBaseVo();
        result.setResultCode(errorCode);
        result.setResultInfo(errorInfo);
        result.setResultFlag(false);
        return buildOpenAPIEntity(result,openAPIEntity);
    }

    public OpenAPIEntity buildOpenAPIEntity(Object data, OpenAPIEntity openAPIEntity){
        MerchantkeyRes merchantkeyRes = getMerchantSecreKeyBymerchantNo(openAPIEntity.getMerchantNo(),openAPIEntity.getAppId());
        String jdprivateKey = merchantkeyRes.getJdprivateKey();
        String aesPrivateKey = merchantkeyRes.getAesPrivatekey();
        try{
            LOGGER.info("交易类型[{}]组装BizEntity开始,请求参数为:{}", ApiTypeEnum.get(openAPIEntity.getApiType()).getName(), GsonUtils.toJson(data));
            //加密
            if(ENCRYPTTYPE.equals(openAPIEntity.getEncryptType())){
                openAPIEntity.setData(OpenApiHelper.encodeBizEntity(JSON.toJSONString(data),aesPrivateKey,openAPIEntity.getCharset()));
            }else {
                openAPIEntity.setData(JSON.toJSONString(data));
            }
            //签名
            openAPIEntity.setSign(OpenApiHelper.sign(openAPIEntity,jdprivateKey));
        }catch (Exception e){
            LOGGER.error("buildOpenAPIEntity error {}",e.getMessage());
            throw SystemExceptionAdapter.initFrontendExceptionInfo(ExceptionConstants.front_service_000001);
        }
        return openAPIEntity;
    }
    protected MerchantkeyRes getMerchantSecreKeyBymerchantNo(String  merchantNo, String appId) {
        return merchantServiceRpc.queryMerchantKey(merchantNo,appId);
    }


    public static  void validateOpenAPIEntity(OpenAPIEntity openAPIEntity){
        if (!StringUtils.hasText( openAPIEntity.getVersion())){
            throw new FrontEndSysException(ExceptionConstants.front_service_000002, "version");
        }
        if (!StringUtils.hasText( openAPIEntity.getAppId())){
            throw new FrontEndSysException(ExceptionConstants.front_service_000002, "appId");
        }
        if (!StringUtils.hasText( openAPIEntity.getTimestamp())){
            throw new FrontEndSysException(ExceptionConstants.front_service_000002, "timestamp");
        }
        if (!StringUtils.hasText( openAPIEntity.getCharset())){
            throw new FrontEndSysException(ExceptionConstants.front_service_000002, "charset");
        }
        if (!StringUtils.hasText( openAPIEntity.getApiType())){
            throw new FrontEndSysException(ExceptionConstants.front_service_000002, "apiType");
        }
        if (!StringUtils.hasText( openAPIEntity.getEncryptType())){
            throw new FrontEndSysException(ExceptionConstants.front_service_000002, "encryptType");
        }
        if (!StringUtils.hasText( openAPIEntity.getSignType())){
            throw new FrontEndSysException(ExceptionConstants.front_service_000002, "signType");
        }
        if (!StringUtils.hasText( openAPIEntity.getData())){
            throw new FrontEndSysException(ExceptionConstants.front_service_000002, "data");
        }
        if (!StringUtils.hasText( openAPIEntity.getMerchantNo())){
            throw new FrontEndSysException(ExceptionConstants.front_service_000002, "merchantNo");
        }
        if (!StringUtils.hasText( openAPIEntity.getSign())){
            throw new FrontEndSysException(ExceptionConstants.front_service_000002, "sign");
        }
    }
}
