package com.zz.opensdk.sdk.domain;

/**
 * @author zhangzuizui
 */
public class ResponseBaseVo {

	/**
     *  响应编码
     */
	private String resultCode;

    /**
     * 响应描述
     */
    private String resultInfo;

    /**
     * 调用成功与否
     */
    private boolean resultFlag;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }

    public boolean isResultFlag() {
        return resultFlag;
    }

    public void setResultFlag(boolean resultFlag) {
        this.resultFlag = resultFlag;
    }
}
