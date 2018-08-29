package com.zz.opensdk.sdk.common.exception;

import com.zz.opensdk.sdk.common.config.DynamicConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangzuizui
 * @date 2018/2/28 11:34
 */
public class FrontEndSysException extends SystemException{
    static Logger LOGGER = LoggerFactory.getLogger(FrontEndSysException.class);
    public static final String ZHANWEI_CHAR = "#";
    /**
     * 不同的错误码，不同的错误提示信息替换
     * @param errorcode
     * @param args
     */
    public FrontEndSysException(String errorcode, String... args) {
        String msg = DynamicConstants.getErrorCodeInfoMapping(errorcode);
        if (args != null && args.length > 0){
            for(int i = 0 ;i< args.length ;i++){
                if (msg.indexOf(ZHANWEI_CHAR) > 0){
                    msg =  msg.replaceFirst(ZHANWEI_CHAR,args[i]);
                }else{
                    break;
                }
            }
        }
        this.setErrorCode(errorcode);
        this.setShowMessage(msg);
    }


    /**
     * 初始化业务系统前端映射异常
     */
    @Override
    public void initSystemException(String errorCode){
        String showMessage = DynamicConstants.getErrorCodeInfoMapping(errorCode);
        this.setErrorCode(errorCode);
        this.setShowMessage(showMessage);

        LOGGER.info("initSystemException,errorCode:{},SystemException.showMessage:{}",errorCode,showMessage);
    }

    public FrontEndSysException() {
    }
}
