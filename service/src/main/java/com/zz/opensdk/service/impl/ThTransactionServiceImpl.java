package com.zz.opensdk.service.impl;

import com.zz.opensdk.sdk.common.config.ApiTypeEnum;
import com.zz.opensdk.sdk.common.config.ExceptionConstants;
import com.zz.opensdk.sdk.common.exception.SystemException;
import com.zz.opensdk.sdk.common.util.GsonUtils;
import com.zz.opensdk.sdk.domain.OpenAPIEntity;
import com.zz.opensdk.service.CToSExecuteService;
import com.zz.opensdk.service.OpenBaseService;
import com.zz.opensdk.service.TemplateMapping;
import com.zz.opensdk.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.velocity.runtime.log.CommonsLogLogChute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author zhangzuizui
 * @date 2018/7/11 20:07
 */
@Service("thTransactionServiceImpl")
public class ThTransactionServiceImpl extends OpenBaseService implements TransactionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThTransactionServiceImpl.class);

    @Autowired
    private TemplateMapping templateMapping;

    @Override
    public OpenAPIEntity transactionService(OpenAPIEntity openAPIEntity) {
        OpenAPIEntity resBizEntity = null;
        try {
            LOGGER.info("交易类型[{}]统一调用服务开始,请求参数为:{}", ApiTypeEnum.get(openAPIEntity.getApiType()).getName(), GsonUtils.toJson(openAPIEntity));
            //校验参数（这里主要校验参数是否为空）
            validateOpenAPIEntity(openAPIEntity);
            //校验商户号是否启用，正向流程需要校验
            checkMerchantNoValid(openAPIEntity.getMerchantNo(), openAPIEntity.getApiType());
            //根据业务类型分流到不同service
            CToSExecuteService cToSExecuteService = templateMapping.getcToSExecuteTemplate().get(openAPIEntity.getApiType().trim());
            String descrptDataStr = openAPIDescriptData(openAPIEntity);
            Object reqObj = cToSExecuteService.getReqObject(descrptDataStr);
            Object resObj = cToSExecuteService.execute(reqObj);
            Object bizObj = cToSExecuteService.getResObject(descrptDataStr, resObj);
            resBizEntity = buildOpenAPIEntity(bizObj,openAPIEntity);
        }catch (SystemException e){
            LOGGER.error("交易类型["+ openAPIEntity.getApiType() + "]统一调用调用服务接口SystemException异常",e);
            resBizEntity = buildErrorResponse(openAPIEntity, e.getErrorCode(), e.getShowMessage());
        }catch (Exception e){
            LOGGER.error("交易类型["+ openAPIEntity.getApiType() + "]统一调用调用服务接口其他异常",e);
            resBizEntity = buildErrorResponse(openAPIEntity, ExceptionConstants.DEFAULT_EROOR, ExceptionConstants.DEFAULT_EROOR_INFO);
        }
        //封装返回
        LOGGER.info("交易类型[{}]统一调用服务结束,返回参数为:{}", ApiTypeEnum.get(openAPIEntity.getApiType()).getName(), GsonUtils.toJson(resBizEntity));
        return resBizEntity;
    }

    private void checkMerchantNoValid(String merchantCode, String tradeType) {

    }
}
