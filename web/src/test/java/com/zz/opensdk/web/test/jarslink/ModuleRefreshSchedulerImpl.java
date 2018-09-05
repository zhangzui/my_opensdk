package com.zz.opensdk.web.test.jarslink;

import com.alipay.jarslink.api.ModuleConfig;
import com.alipay.jarslink.api.impl.AbstractModuleRefreshScheduler;
import com.alipay.jarslink.api.impl.ModuleClassLoader;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.springframework.beans.CachedIntrospectionResults;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleRefreshSchedulerImpl extends AbstractModuleRefreshScheduler {

    @Override
    public List<ModuleConfig> queryModuleConfigs() {
        return ImmutableList.of(ModuleRefreshSchedulerImpl.buildModuleConfig());
    }

    public static ModuleConfig buildModuleConfig() {

        //获取模块的ClassLoader
       // URL demoModule = Thread.currentThread().getContextClassLoader().getResource("/spring/my_jarslink-1.0.0.jar");

        List<URL> moduleUrl = Lists.newArrayList();
        try {
            URL url = new URL("file:/D:/Users/my_opensdk/web/src/test/resources/spring/my_jarslink-1.0.0.jar");
//            URL url = new URL("file:/D:/User/zhangzuigit/jarslink/jarslink-api/target/test-classes/jarslink-module-demo-1.0.0.jar");
            moduleUrl.add(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        MyModuleClassLoader myModuleClassLoader = new MyModuleClassLoader(moduleUrl.toArray(new URL[]{}),Thread.currentThread().getContextClassLoader());
        Thread.currentThread().setContextClassLoader(myModuleClassLoader);

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        URL demoModule = classLoader.getResource("spring/my_jarslink-1.0.0.jar");
        ModuleConfig moduleConfig = new ModuleConfig();

        String scanBase = "com.zz.opensdk.jarslink.main";

        moduleConfig.addScanPackage(scanBase);
        moduleConfig.removeScanPackage(scanBase);
        Map<String, Object> properties = new HashMap();
        moduleConfig.withEnabled(true).
                withVersion("1.0.0.20170621").
                withOverridePackages(ImmutableList.of("com.zz.opensdk.jarslink.action")).
                withProperties(properties);

        moduleConfig.setOverridePackages(ImmutableList.of("com.zz.opensdk.jarslink.action"));
        moduleConfig.setName("demo");
        moduleConfig.setEnabled(true);
        moduleConfig.setVersion("1.0.0.20170621");
        properties.put("url", "127.0.0.1");
        moduleConfig.setProperties(properties);
        moduleConfig.setModuleUrl(ImmutableList.of(demoModule));

        CachedIntrospectionResults.clearClassLoader(myModuleClassLoader);

        return moduleConfig;
    }
}