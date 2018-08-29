package com.zz.opensdk.web.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(10000)
public class BaseAop {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseAop.class);

	@Around(value = "execution(* com.zz.opensdk.web.controller.*.*(..))", argNames = "proceedingJoinPoint")
	public Object executeAround(ProceedingJoinPoint proceedingJoinPoint){
		Object[] args = proceedingJoinPoint.getArgs();
		MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
		String methodName = signature.getMethod().getName();
		Object result = null;
		try {
			try {
				LOGGER.info("{}请求参数为：{}", methodName, JSON.toJSONString(args));
				result = proceedingJoinPoint.proceed();
				LOGGER.info("{}响应参数为：{}", methodName, JSON.toJSONString(result));
			} catch (Throwable throwable){
				LOGGER.error("异常处理", throwable);
			}
		} catch (Exception e) {
			LOGGER.error("处理AppRuntimeExceptionAop时，出现异常", e);
		}
		return result;
	}

}
