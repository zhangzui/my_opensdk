package com.zz.opensdk.sdk.handle;

/**
 * 验签器接口
 *
 * @author zhangzuizui
 * @date 2018/7/11 12:13
 */
public interface SignChecker {

    boolean check(String sourceContent, String signature, String signType, String charset);
}