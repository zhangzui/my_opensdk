package com.zz.opensdk.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Enumeration;

public class RequestUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestUtils.class);

    private static final String CHARSET = "UTF-8";

    public static String getCurrentUrl(HttpServletRequest request) {
        String queryString = request.getQueryString();
        String currentUrl = queryString != null ? request.getRequestURL() + "?" + request.getQueryString() : request.getRequestURL().toString();
        return currentUrl;
    }

    public static String getCurrentUrl(HttpServletRequest request, boolean isHttps) {
        String queryString = request.getQueryString();
        String currentUrl = queryString != null ? request.getRequestURL() + "?" + request.getQueryString() : request.getRequestURL().toString();
        if (isHttps) {
            currentUrl = currentUrl.replaceFirst("http", "https");
        }
        return currentUrl;
    }

	/**
     * 获取客户端ip
     */
    public static String getClientIp() {
    	HttpServletRequest httpServletRequest = getHttpServletRequest();
        String ip = httpServletRequest.getRemoteAddr();

        try{
            if (httpServletRequest.getHeader("j-forwarded-for") != null
                    && !"unknown".equalsIgnoreCase(httpServletRequest.getHeader("j-forwarded-for"))) {
                ip = ip + "," + httpServletRequest.getHeader("j-forwarded-for");
                LOGGER.info("JForwardedFor:"+httpServletRequest.getHeader("j-forwarded-for")+"  getRemoteAddr = "+httpServletRequest.getRemoteAddr());
            }else{
                if (httpServletRequest.getHeader("x-forwarded-for") != null
                        && !"unknown".equalsIgnoreCase(httpServletRequest.getHeader("x-forwarded-for"))) {
                    String xforwardIp=httpServletRequest.getHeader("x-forwarded-for");
                    //处理PIC机房IP的问题
                    if(xforwardIp.contains(",")){
                        xforwardIp=xforwardIp.substring(0,xforwardIp.indexOf(",")).trim();
                    }
                    ip = ip + "," + xforwardIp;
                }
                LOGGER.info("JForwardedFor:"+httpServletRequest.getHeader("x-forwarded-for")+"  getRemoteAddr = "+httpServletRequest.getRemoteAddr());
            }


            StringBuffer sbHeaderInfo = new StringBuffer();
            Enumeration enumeration=httpServletRequest.getHeaderNames();
            while (enumeration.hasMoreElements()) {
                String headerName = (String) enumeration.nextElement();
                String headerValue = httpServletRequest.getHeader(headerName);
                sbHeaderInfo.append("headerName:"+headerName+" headerValue:"+headerValue+"\n");
            }
            LOGGER.info("sbHeaderInfo :" +sbHeaderInfo+"  getClientIp :"+ip);
        }catch(Exception exception){
            LOGGER.error("getHeaderNames exception ",exception);
            return "127.0.0.1";
        }

        if(StringUtils.hasText(ip)){
            ip=ip.substring(ip.lastIndexOf(",") + 1 ).trim();
        }
        return ip;
    }

    /**
     * 获取客户端ip
     */
    public static String getSessionId() {
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        String sessionId = httpServletRequest.getSession().getId();
        return sessionId;
    }

    public static HttpServletRequest getHttpServletRequest() {
    	ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    	HttpServletRequest request = servletRequestAttributes.getRequest();
    	return request;
    }

    /**
     * 获取请求Body
     *
     * @param request
     * @return
     */
    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(CHARSET)));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            LOGGER.error("getBodyString ioException");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.error("inputStream.close ioException");
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LOGGER.error("reader.close ioException");
                }
            }
        }
        return sb.toString();
    }
}
