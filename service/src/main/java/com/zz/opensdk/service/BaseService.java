package com.zz.opensdk.service;

import com.zz.opensdk.web.test.rpc.MerchantServiceRpc;
import com.zz.opensdk.web.test.rpc.domain.MerchantkeyRes;
import com.zz.opensdk.sdk.common.config.ApiTypeEnum;
import com.zz.opensdk.sdk.common.config.ExceptionConstants;
import com.zz.opensdk.sdk.common.exception.SystemExceptionAdapter;
import com.zz.opensdk.sdk.common.util.GsonUtils;
import com.zz.opensdk.sdk.domain.OpenAPIEntity;
import com.zz.opensdk.sdk.domain.ResponseBaseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * @author zhangzuizui
 */
public abstract  class BaseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    private MerchantServiceRpc merchantServiceRpc;

    public String descriptData(OpenAPIEntity bizEntity) {
        MerchantkeyRes merchantkeyRes = getMerchantSecreKeyBymerchantNo(bizEntity.getMerchantNo(),bizEntity.getAppId());
        String privateKey = merchantkeyRes.getAesPrivatekey();
        String saltKey = merchantkeyRes.getSalt();
        LOGGER.info("交易类型[{}]解析BizEntity开始,descriptData请求参数为:{}", ApiTypeEnum.get(bizEntity.getApiType()).getName(), GsonUtils.toJson(bizEntity));
        // 验签

        //data解密
        String data = "";
        //3.data验证
        if (!StringUtils.hasText(data)){
            throw SystemExceptionAdapter.initFrontendExceptionInfo(ExceptionConstants.front_service_000006);
        }
        LOGGER.info("交易类型[{}]解析BizEntity结束,descriptData返回参数为:{}", ApiTypeEnum.get(bizEntity.getApiType()).getName(), data);
        return data;
    }

    public OpenAPIEntity buildErrorResponse(OpenAPIEntity bizEntity, String errorCode, String errorInfo) {
        ResponseBaseVo result = new ResponseBaseVo();
        result.setResultCode(errorCode);
        result.setResultInfo(errorInfo);
        result.setResultFlag(false);
        return buildBizEntity(result,bizEntity);
    }

    public OpenAPIEntity buildBizEntity(Object data,OpenAPIEntity bizEntity){
        OpenAPIEntity resBizEntity = new OpenAPIEntity();
        MerchantkeyRes merchantkeyRes = getMerchantSecreKeyBymerchantNo(bizEntity.getMerchantNo(),bizEntity.getAppId());
        String privateKey = merchantkeyRes.getAesPrivatekey();
        String saltKey = merchantkeyRes.getSalt();
        try{
            LOGGER.info("交易类型[{}]组装BizEntity开始,请求参数为:{}", ApiTypeEnum.get(bizEntity.getApiType()).getName(), GsonUtils.toJson(data));
            resBizEntity.setCharset(bizEntity.getCharset());
            resBizEntity.setVersion(bizEntity.getVersion());
            resBizEntity.setApiType(bizEntity.getApiType());
            resBizEntity.setMerchantNo(bizEntity.getMerchantNo());
            //BizEntityUtils.encodeBizEntity(data,bizEntity,privateKey,saltKey);
        }catch (Exception e){
            LOGGER.error("组装BizEntity异常",e);
            throw SystemExceptionAdapter.initFrontendExceptionInfo(ExceptionConstants.front_service_000001);
        }
        return bizEntity;
    }

    protected MerchantkeyRes getMerchantSecreKeyBymerchantNo(String  merchantNo, String  appid) {
        return merchantServiceRpc.queryMerchantKey(merchantNo,appid);
    }

    public static  void validateBizEntity(OpenAPIEntity bizEntity){

    }
}
