package com.zz.opensdk.service;

import com.zz.opensdk.sdk.domain.OpenAPIEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author zhangzuizui
 */
@Component
public abstract class SToCExecuteService<T1,T2> extends BaseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SToCExecuteService.class);
    public abstract T2 getResObject(String resStr);

    public abstract OpenAPIEntity execute(String url , OpenAPIEntity bizEntity);
}
