package com.zz.opensdk.service.impl;

import com.zz.opensdk.service.CToSExecuteService;
import com.zz.opensdk.sdk.common.config.ExceptionConstants;
import com.zz.opensdk.sdk.domain.ResponseBaseVo;
import org.springframework.stereotype.Component;

/**
 * @author zhangzuizui
 * @date 2018/6/25 11:33
 */
@Component("templateService")
public class TemplateServiceImpl extends CToSExecuteService<Object,Object,Object> {
    /**
     * 请求参数明文
     * 构造rpc请求入参
     * @param reqStr
     * @return
     */
    @Override
    public Object getReqObject(String reqStr) {
        System.out.println("前端请求参数明文"+reqStr);
        System.out.println("组装服务端请求参数"+reqStr);
        return reqStr;
    }

    /**
     * 获取rpc返回参数
     * 并且转换成前端所需vo
     * @param reqStr
     * @param resObj
     * @return
     */
    @Override
    public Object getResObject(String reqStr, Object resObj) {
        ResponseBaseVo responseBaseVo = new ResponseBaseVo();
        responseBaseVo.setResultCode(ExceptionConstants.DEFAULT_SUCCESS);
        responseBaseVo.setResultInfo(ExceptionConstants.DEFAULT_SUCCESS_INFO);
        responseBaseVo.setResultFlag(true);
        return responseBaseVo;
    }

    /**
     * 执行调用
     * @param reqObj
     * @return
     */
    @Override
    public Object execute(Object reqObj) {
        System.out.println("执行调用，请求参数"+reqObj);
        return "success";
    }
}
