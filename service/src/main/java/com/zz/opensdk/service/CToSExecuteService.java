package com.zz.opensdk.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author zhangzuizui
 */
@Component
public abstract class CToSExecuteService<T1,T2,T3> extends BaseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CToSExecuteService.class);

    public abstract T1 getReqObject(String reqStr);

    public abstract T3 getResObject(String reqStr,T2 resObj);

    public abstract  T2 execute(T1 reqObj);
}
