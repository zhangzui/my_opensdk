package com.zz.opensdk.sdk.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * 启动初始化UCC的配置文件，填充DynamicConstants中的配置信息数据结构
 * 后续只需要对DynamicConstants 中的数据进行加工即可
 * @author zhangzuizui
 */
@Component
public class UccConfigListener implements InitializingBean{
    static final Logger logger = LoggerFactory.getLogger(UccConfigListener.class);

    @Value("#{errorCodeInfoMapping}")
    private Properties errorCodeInfoMapping;

    @Value("#{innerCodeMapping}")
    private Properties innerCodeMapping;

    @Override
    public void afterPropertiesSet() throws Exception {
        DynamicConstants.errorCodeInfoMapping = errorCodeInfoMapping;
        DynamicConstants.innerCodeMapping = innerCodeMapping;
        DynamicConstants.printConf();
    }
}
