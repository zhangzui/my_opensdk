package com.zz.opensdk.sdk.handle;

import com.zz.opensdk.sdk.utils.EncryptUtils;

/**
 * @author zhangzuizui
 * @date 2018/7/11 12:13
 */
public class DefaultDecryptor implements Decryptor {

    private String encryptKey;

    public DefaultDecryptor(String encryptKey) {
        this.encryptKey = encryptKey;
    }

    @Override
    public String decrypt(String encryptContent, String encryptType, String charset) {
        String decryptContent = null;
        try {
            decryptContent = EncryptUtils.decryptContent(encryptContent, encryptType,
                    encryptKey, charset);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return decryptContent;
    }

    /**
     * Getter method for property <tt>encryptKey</tt>.
     *
     * @return property value of encryptKey
     */
    public String getEncryptKey() {
        return encryptKey;
    }

    /**
     * Setter method for property <tt>encryptKey</tt>.
     *
     * @param encryptKey  value to be assigned to property encryptKey
     */
    public void setEncryptKey(String encryptKey) {
        this.encryptKey = encryptKey;
    }
}