package com.zz.opensdk.service;


import com.zz.opensdk.sdk.domain.OpenAPIEntity;

public interface TransactionService {

    /**
     * 泰国交易
     * @param openAPIEntity
     * @return
     */
    OpenAPIEntity transactionService(OpenAPIEntity openAPIEntity);
}
