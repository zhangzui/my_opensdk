package com.zz.opensdk.sdk.handle;

/**
 * 加签器接口
 */
public interface Signer {

    String sign(String sourceContent, String signType, String charset);
}