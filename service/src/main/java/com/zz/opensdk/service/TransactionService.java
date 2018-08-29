package com.zz.opensdk.service;


import com.zz.opensdk.sdk.domain.OpenAPIEntity;

/**
 * 泰国交易服务
 * @author zhangzuizui
 * @date 2018/7/11 20:06
 */
public interface TransactionService {

    /**
     * 泰国交易
     * @param openAPIEntity
     * @return
     */
    OpenAPIEntity transactionService(OpenAPIEntity openAPIEntity);
}
