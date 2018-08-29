package com.zz.opensdk.sdk.common.exception;

import com.zz.opensdk.sdk.common.config.DynamicConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 业务系统的异常重新映射
 *
 * 将业务系统的码，copy一份，将前端映射码放在info前面，有助于问题定位，不需要再去业务系统看错误码解释信息。
 *
 * eg：ocf_rpc_000001=999999_redis异常
 *
 * @author zhangzuizui
 * @date 2017/11/22
 */
public class BizSystemException extends SystemException{

    static Logger LOGGER = LoggerFactory.getLogger(BizSystemException.class);

    /**
     * 初始化业务系统前端映射异常
     */
    @Override
    public void initSystemException(String errorCode){
        String innerCode = DynamicConstants.getInnerCodeMapping(errorCode);
        String showMessage = DynamicConstants.getErrorCodeInfoMapping(innerCode);
        this.setErrorCode(innerCode);
        this.setShowMessage(showMessage);
        LOGGER.info("initSystemException,errorCode:{},SystemException.innerCode:{},SystemException.newMessage:{}",errorCode,innerCode,showMessage);
    }

    public BizSystemException(String errorCode, String showMessage) {
        super(errorCode, showMessage);
    }

    public BizSystemException() {
    }
}
