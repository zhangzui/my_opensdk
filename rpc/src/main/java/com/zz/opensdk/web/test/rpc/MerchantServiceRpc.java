package com.zz.opensdk.web.test.rpc;

import com.zz.opensdk.web.test.rpc.domain.MerchantkeyRes;

/**
 * @author zhangzuizui
 * @date 2018/6/25 15:14
 */
public interface MerchantServiceRpc {

    MerchantkeyRes queryMerchantKey(String merchantNo, String appId);
}
