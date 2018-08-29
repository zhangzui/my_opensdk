
package com.zz.opensdk.sdk.handle;

/**
 * 解密器接口
 */
public interface Decryptor {

    String decrypt(String encryptContent, String encryptType, String charset);
}