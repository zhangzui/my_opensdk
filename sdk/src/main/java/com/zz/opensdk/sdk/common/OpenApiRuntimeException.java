package com.zz.opensdk.sdk.common;


/**
 * @author zhangzuizui
 * @date 2018/7/11 12:13
 */
public class OpenApiRuntimeException extends Exception {

    private static final long serialVersionUID = -238091758285157331L;

    private String errCode;
    private String errMsg;

    public OpenApiRuntimeException() {
        super();
    }

    public OpenApiRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public OpenApiRuntimeException(String message) {
        super(message);
    }

    public OpenApiRuntimeException(Throwable cause) {
        super(cause);
    }

    public OpenApiRuntimeException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

}