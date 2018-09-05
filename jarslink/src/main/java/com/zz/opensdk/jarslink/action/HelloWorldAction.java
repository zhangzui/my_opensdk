package com.zz.opensdk.jarslink.action;

import com.alipay.jarslink.api.Action;

/**
 * 一个简单的Action实现
 */
public class HelloWorldAction implements Action<String, String> {

    @Override
    public String execute(String res) {

        return "new-success1212121:"+res;
    }

    @Override
    public String getActionName() {
        return "helloWorld";
    }
}