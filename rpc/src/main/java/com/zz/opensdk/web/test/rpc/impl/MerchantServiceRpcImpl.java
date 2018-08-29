package com.zz.opensdk.web.test.rpc.impl;

import com.zz.opensdk.web.test.rpc.MerchantServiceRpc;
import com.zz.opensdk.web.test.rpc.domain.MerchantkeyRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author zhangzuizui
 * @date 2018/6/25 15:15
 */
@Component("merchantServiceRpc")
public class MerchantServiceRpcImpl implements MerchantServiceRpc {
    private static final Logger LOGGER = LoggerFactory.getLogger(MerchantServiceRpcImpl.class);

    @Override
    public MerchantkeyRes queryMerchantKey(String merchantNo, String appId) {
        MerchantkeyRes merchantkeyRes = new MerchantkeyRes();
        merchantkeyRes.setJdprivateKey("");
        merchantkeyRes.setJdPublicKey("");
        merchantkeyRes.setMerPublicKey("");
        merchantkeyRes.setAesPrivatekey("");
        merchantkeyRes.setStatus(1);
        merchantkeyRes.setSalt("");
        merchantkeyRes.setMerchantId(merchantNo);
        merchantkeyRes.setAppId(appId);
        return merchantkeyRes;
    }
}
