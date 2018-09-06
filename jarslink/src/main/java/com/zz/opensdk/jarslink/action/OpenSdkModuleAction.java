package com.zz.opensdk.jarslink.action;

import com.alipay.jarslink.api.Action;
import com.zz.opensdk.sdk.common.config.ApiTypeEnum;
import com.zz.opensdk.sdk.common.config.ExceptionConstants;
import com.zz.opensdk.sdk.common.exception.SystemException;
import com.zz.opensdk.sdk.common.util.GsonUtils;
import com.zz.opensdk.sdk.domain.OpenAPIEntity;
import com.zz.opensdk.sdk.domain.ResponseBaseVo;
import com.zz.opensdk.service.CToSExecuteService;
import com.zz.opensdk.service.OpenBaseService;
import com.zz.opensdk.service.TemplateMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhangzuizui
 * @date 2018/9/6 16:51
 */
public class OpenSdkModuleAction extends OpenBaseService implements Action<OpenAPIEntity, OpenAPIEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenSdkModuleAction.class);

    @Autowired
    private TemplateMapping templateMapping;
    @Override
    public OpenAPIEntity execute(OpenAPIEntity openAPIEntity) {
        OpenAPIEntity resBizEntity = null;
        try {
            LOGGER.info("openAPIEntity-------new");
            LOGGER.info("交易类型[{}]统一调用服务开始,请求参数为:{}", ApiTypeEnum.get(openAPIEntity.getApiType()).getName(), GsonUtils.toJson(openAPIEntity));

            //CToSExecuteService cToSExecuteService = templateMapping.getcToSExecuteTemplate().get(openAPIEntity.getApiType().trim());
            //String descrptDataStr = openAPIDescriptData(openAPIEntity);
            //Object reqObj = cToSExecuteService.getReqObject(descrptDataStr);
            //Object resObj = cToSExecuteService.execute(reqObj);
            //Object bizObj = cToSExecuteService.getResObject(descrptDataStr, resObj);

            ResponseBaseVo responseBaseVo = new ResponseBaseVo();
            responseBaseVo.setResultCode("new00000");
            responseBaseVo.setResultInfo("new-success");
            responseBaseVo.setResultFlag(true);

            resBizEntity = buildOpenAPIEntity(responseBaseVo,openAPIEntity);
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

    @Override
    public String getActionName() {
        return "opensdk";
    }
}
