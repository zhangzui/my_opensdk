package com.zz.opensdk.web.test.rpc;

import com.alibaba.fastjson.JSON;
import com.zz.opensdk.web.test.rpc.domain.MerchantkeyRes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhangzuizui
 * @date 2018/2/28 11:30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring-config.xml"})
public class TestMerchantService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestMerchantService.class);

    @Autowired
    private MerchantServiceRpc merchantServiceRpc;

    @Test
    public void test002(){
        MerchantkeyRes res = merchantServiceRpc.queryMerchantKey("360087641000000252","20180723000014");
        System.out.println(JSON.toJSONString(res));
    }
    @Test
    public void test003(){
        MerchantkeyRes res = merchantServiceRpc.queryMerchantKey("360087641000000005","20180719000000");
        System.out.println(JSON.toJSONString(res));
    }


}
