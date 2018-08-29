package com.zz.opensdk.sdk.common.exception;

/**
 * 异常适配器
 * 根据不同的异常类和异常码，获取对应的映射文件的前端码和对前端用户友好一点文案信息
 *
 * @author zhangzuizui
 * @date 2018/2/28 11:52
 */
public class SystemExceptionAdapter {
    /**
     * 初始化异常类对象
     * @param errorCode
     * @return
     */
    public static SystemException initBizExceptionInfo(String errorCode, String... args) {
        //基于class对象，创建异常类对象
        BizSystemException bizSystemException = new BizSystemException();
        //初始化异常类
        bizSystemException.initSystemException(errorCode);

        return bizSystemException;
    }


    /**
     * 初始异常
     */
    public static SystemException initFrontendExceptionInfo(String errorCode) {
        FrontEndSysException frontEndSysException = new FrontEndSysException();
        frontEndSysException.initSystemException(errorCode);
        return frontEndSysException;
    }

    /**
     * 初始异常
     */
    public static SystemException initFrontendExceptionInfo(String errorCode,String errorInfo) {
        FrontEndSysException frontEndSysException = new FrontEndSysException(errorCode,errorInfo);
        return frontEndSysException;
    }

}
