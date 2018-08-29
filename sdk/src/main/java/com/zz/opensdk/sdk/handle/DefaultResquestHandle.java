package com.zz.opensdk.sdk.handle;

/**
 * @author zhangzuizui
 * @date 2018/7/11 14:41
 */
public class DefaultResquestHandle extends AbstractResquestHandle{

    /**
     * merchant private key
     */
    private String privateKey;

    /**
     * body encryptKey (The rule is AES)
     */
    private String encryptKey;

    /**
     * JV side public key
     */
    private String jdPublicKey;

    /**
     * The utils for decrypt , encrypt and sign check
     */
    private Decryptor decryptor;
    private Encryptor encryptor;
    private SignChecker signChecker;
    private Signer signer;

    public DefaultResquestHandle(String version, String tradeType, String appId, String merchantNo, String charset, String privateKey,
                                 String jdPublicKey, String encryptKey, String url) {
        super(version,tradeType, appId, charset,merchantNo,url);
        this.privateKey = privateKey;
        this.signer = new DefaultSigner(privateKey);
        this.jdPublicKey = jdPublicKey;
        this.signChecker = new DefaultSignChecker(jdPublicKey);
        this.encryptor = new DefaultEncryptor(encryptKey);
        this.decryptor = new DefaultDecryptor(encryptKey);
    }
    @Override
    public Signer getSigner() {
        return signer;
    }

    @Override
    public SignChecker getSignChecker() {
        return signChecker;
    }

    @Override
    public Encryptor getEncryptor() {
        return encryptor;
    }

    @Override
    public Decryptor getDecryptor() {
        return decryptor;
    }

}
