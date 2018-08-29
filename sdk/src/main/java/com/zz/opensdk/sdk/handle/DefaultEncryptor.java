package com.zz.opensdk.sdk.handle;


import com.zz.opensdk.sdk.common.OpenApiRuntimeException;
import com.zz.opensdk.sdk.utils.EncryptUtils;

/**
 * @author zhangzuizui
 * @date 2018/7/11 12:13
 */
public class DefaultEncryptor implements Encryptor {

    private String encryptKey;

    public DefaultEncryptor(String encryptKey) {
        this.encryptKey = encryptKey;
    }

    @Override
    public String encrypt(String sourceContent, String encryptType, String charset) {
        String encryptContent = null;
        try {
            encryptContent = EncryptUtils.encryptContent(sourceContent, encryptType,
                    this.encryptKey, charset);
        } catch (OpenApiRuntimeException e) {
            throw new RuntimeException(e);
        }
        return encryptContent;
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