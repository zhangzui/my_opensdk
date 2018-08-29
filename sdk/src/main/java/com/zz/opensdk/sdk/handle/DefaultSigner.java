package com.zz.opensdk.sdk.handle;


import com.zz.opensdk.sdk.common.OpenApiRuntimeException;
import com.zz.opensdk.sdk.utils.SignUtils;

/**
 * 默认加签器
 * @author zhangzuizui
 * @date 2018/7/11 12:13
 */
public class DefaultSigner implements Signer {

    private String privateKey;

    public DefaultSigner(String privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public String sign(String sourceContent, String signType, String charset) {
        String sign = null;
        try {
            sign = SignUtils.rsaSign(sourceContent, this.privateKey, charset, signType);
        } catch (OpenApiRuntimeException e) {
            throw new RuntimeException(e);
        }
        return sign;
    }

    /**
     * Getter method for property <tt>privateKey</tt>.
     *
     * @return property value of privateKey
     */
    public String getPrivateKey() {
        return privateKey;
    }

    /**
     * Setter method for property <tt>privateKey</tt>.
     *
     * @param privateKey  value to be assigned to property privateKey
     */
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}