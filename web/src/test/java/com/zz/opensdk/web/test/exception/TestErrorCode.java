package com.zz.opensdk.web.test.exception;

import com.zz.opensdk.sdk.common.exception.BizSystemException;
import com.zz.opensdk.sdk.common.exception.SystemException;
import com.zz.opensdk.sdk.common.exception.SystemExceptionAdapter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhangzuizui
 * @date 2018/2/28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring-config.xml"})
public class TestErrorCode {

    @Test
    public void testErrorCodeInfo(){
        BizSystemException systemException = new BizSystemException("","");
        //初始化异常类
        systemException.initSystemException("ocf_sys_000015");
        System.out.println(systemException.getErrorCode());
        System.out.println(systemException.getShowMessage());
    }

    @Test
    public void testBizErrorCodeInfo001(){
        SystemException systemException = SystemExceptionAdapter.initBizExceptionInfo("ocf_sys_000015");
    }
    @Test
    public void testFrontEndErrorCodeInfo(){
        SystemException systemException = SystemExceptionAdapter.initFrontendExceptionInfo("-10001");
    }

}
