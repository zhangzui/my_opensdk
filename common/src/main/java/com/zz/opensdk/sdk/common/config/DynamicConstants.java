package com.zz.opensdk.sdk.common.config;

import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author zhangzuizui
 * @date 2018/2/28
 */
public class DynamicConstants {
    static Logger LOGGER = LoggerFactory.getLogger(DynamicConstants.class);

    static Set<String> openKey = Sets.newHashSet("ON", "1", "OPEN");



    static Properties errorCodeInfoMapping;
    static Properties innerCodeMapping;


    /**
     * 业前端系统的错误码Info映射
     * @param code
     * @return
     */
    public static String getErrorCodeInfoMapping(String code) {
        return errorCodeInfoMapping.getProperty(code, code);
    }
    public static String getInnerCodeMapping(String errorCode) {
        return innerCodeMapping.getProperty(errorCode, ExceptionConstants.DEFAULT_EROOR);
    }

    /**
     * 打印配置信息
     */
    public static void printConf() {
        LOGGER.info("printConf-errorCodeInfoMapping\t\t\t:{}", errorCodeInfoMapping);
    }

}
