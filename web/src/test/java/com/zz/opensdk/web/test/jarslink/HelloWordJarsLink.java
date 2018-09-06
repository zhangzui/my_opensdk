package com.zz.opensdk.web.test.jarslink;

import com.alipay.jarslink.api.Module;
import com.alipay.jarslink.api.ModuleConfig;
import com.alipay.jarslink.api.ModuleLoader;
import com.alipay.jarslink.api.ModuleManager;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URL;
import java.util.concurrent.Executors;

/**
 * @author zhangzuizui
 * @date 2018/9/5 16:18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/jarslink.xml"})
public class HelloWordJarsLink {

    @Autowired
    private ModuleLoader moduleLoader;
    @Autowired
    private ModuleManager moduleManager;

    @Test
    public void test1() throws InterruptedException {
        while (true){
            //加载和注册模块
            Module module = moduleLoader.load(ModuleRefreshSchedulerImpl.buildModuleConfig());
            moduleManager.register(module);
            String result = module.doAction("helloWorld", "zzz");
            System.out.println("result = " + result);

            Thread.sleep(1000);
            //卸载模块
            moduleLoader.unload(module);
        }
    }
    @Test
    public void test1_1() throws InterruptedException {
        //加载和注册模块
        Module module = moduleLoader.load(ModuleRefreshSchedulerImpl.buildModuleConfig());
        moduleManager.register(module);
        String result = module.doAction("helloWorld", "zzz");
        System.out.println("result = " + result);

        Thread.sleep(5000);
        //卸载模块
        moduleLoader.unload(module);

        //加载和注册模块
        Module module2 = moduleLoader.load(ModuleRefreshSchedulerImpl.buildModuleConfig2());
        moduleManager.register(module2);
        String result2 = module2.doAction("helloWorld", "zzz");
        System.out.println("result2 = " + result2);

        Thread.sleep(1000);
        //卸载模块
        moduleLoader.unload(module);
    }

    @Test
    public void test0() throws InterruptedException {
        //加载和注册模块
        Module module = moduleLoader.load(ModuleRefreshSchedulerImpl.buildModuleConfig());
        moduleManager.register(module);
        String result = module.doAction("helloWorld", "zzz");
        System.out.println("result = " + result);

        Thread.sleep(5000);
        //卸载模块
        moduleLoader.unload(module);
    }

    @Test
    public void test2() {
        //URL demoModule = Thread.currentThread().getContextClassLoader().getResource("/spring/my_jarslink-1.0.0.jar");
        URL demoModule = HelloWordJarsLink.class.getClassLoader().getResource("spring/my_jarslink-1.0.0.jar");
        ModuleConfig moduleConfig = new ModuleConfig();
        moduleConfig.setName("HelloWorld-action");
        moduleConfig.setEnabled(true);
        moduleConfig.setVersion("1.0.0");
        moduleConfig.setProperties(ImmutableMap.of("svnPath", new Object()));
        moduleConfig.setModuleUrl(ImmutableList.of(demoModule));
        //扫描模块下的Action
        moduleConfig.addScanPackage("com.zz.opensdk.jarslink");
        Module module = moduleLoader.load(moduleConfig);
        moduleManager.register(module);
        System.out.println("result: " + module.doAction("helloWorld", "zzz"));
    }

}


