package com.zz.opensdk.web.test.rpc.domain;

/**
 * @author zhangzuizui
 * @date 2018/7/3 15:58
 */
public class MerchantkeyRes {

    private String merchantId;
    private String shortName;
    private Integer status;
    private String appId;
    private String appName;
    private String salt;
    private Integer keyStatus;
    private Integer defaultFlag;

    private String jdPublicKey;
    private String jdprivateKey;
    private String merPublicKey;
    private String aesPrivatekey;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getKeyStatus() {
        return keyStatus;
    }

    public void setKeyStatus(Integer keyStatus) {
        this.keyStatus = keyStatus;
    }

    public Integer getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Integer defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public String getJdPublicKey() {
        return jdPublicKey;
    }

    public void setJdPublicKey(String jdPublicKey) {
        this.jdPublicKey = jdPublicKey;
    }

    public String getJdprivateKey() {
        return jdprivateKey;
    }

    public void setJdprivateKey(String jdprivateKey) {
        this.jdprivateKey = jdprivateKey;
    }

    public String getMerPublicKey() {
        return merPublicKey;
    }

    public void setMerPublicKey(String merPublicKey) {
        this.merPublicKey = merPublicKey;
    }

    public String getAesPrivatekey() {
        return aesPrivatekey;
    }

    public void setAesPrivatekey(String aesPrivatekey) {
        this.aesPrivatekey = aesPrivatekey;
    }
}
