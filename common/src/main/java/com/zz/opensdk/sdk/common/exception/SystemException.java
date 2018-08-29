package com.zz.opensdk.sdk.common.exception;

import org.springframework.util.StringUtils;

/**
 * 前端系统异常
 * 不同的系统或者异常分类，可以实现该抽象类
 *
 * @author zhangzuizui
 * @date 2017/11/22
 */
public abstract class SystemException extends RuntimeException{
    public static final String pre_str = "(";
    public static final String suff_str = ")";

    private String errorCode;
    private String showMessage;

    protected SystemException() {
    }

    public SystemException(String errorCode, String showMessage) {
        this.errorCode = errorCode;
        this.showMessage = showMessage;
    }


    /**
     * 初始化异常
     * @param errorCode
     */
    public abstract void initSystemException(String errorCode);

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getShowMessage() {
        return showMessage;
    }

    public void setShowMessage(String showMessage) {
        this.showMessage = showMessage;
    }
}
