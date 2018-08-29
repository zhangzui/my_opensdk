package com.zz.opensdk.sdk.handle;


import com.zz.opensdk.sdk.domain.RequestBaseVo;
import com.zz.opensdk.sdk.domain.ResponseBaseVo;

/**
 * @author zhangzuizui
 * @date 2018/7/11 14:14
 */
public interface RequestHandle {

    ResponseBaseVo handle(RequestBaseVo openAPIEntity);

}
