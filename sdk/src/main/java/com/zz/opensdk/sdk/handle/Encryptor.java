package com.zz.opensdk.sdk.handle;

/**
 * 加密器接口
 * @author zhangzuizui
 * @date 2018/7/11 12:13
 */
public interface Encryptor {


    String encrypt(String sourceContent, String encryptType, String charset);
}