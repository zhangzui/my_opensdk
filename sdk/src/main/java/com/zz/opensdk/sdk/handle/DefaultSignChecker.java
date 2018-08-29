package com.zz.opensdk.sdk.handle;


import com.zz.opensdk.sdk.common.OpenApiRuntimeException;
import com.zz.opensdk.sdk.utils.SignUtils;

/**
 * @author zhangzuizui
 * @date 2018/7/11 12:13
 */
public class DefaultSignChecker implements SignChecker {

    private String jvPublicKey;

    public DefaultSignChecker(String jvPublicKey) {
        this.jvPublicKey = jvPublicKey;
    }

    @Override
    public boolean check(String sourceContent, String signature, String signType, String charset) {
        boolean success = false;
        try {
            success = SignUtils.rsaCheck(sourceContent, signature, jvPublicKey, charset, signType);
        } catch (OpenApiRuntimeException e) {
            throw new RuntimeException(e);
        }
        return success;
    }

    /**
     * Getter method for property <tt>jvPublicKey</tt>.
     *
     * @return property value of jvPublicKey
     */
    public String getJvPublicKey() {
        return jvPublicKey;
    }

    /**
     * Setter method for property <tt>jvPublicKey</tt>.
     *
     * @param jvPublicKey  value to be assigned to property jvPublicKey
     */
    public void setJvPublicKey(String jvPublicKey) {
        this.jvPublicKey = jvPublicKey;
    }
}