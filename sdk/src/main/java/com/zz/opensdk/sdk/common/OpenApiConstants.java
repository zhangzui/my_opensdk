package com.zz.opensdk.sdk.common;

/**
 * @author zhangzuizui
 * @date 2018/7/11 12:13
 */
public class OpenApiConstants {

    public static final String SIGN_TYPE                      = "signType";
    public static final String SIGN_TYPE_RSA                  = "RSA";

    /**
     * sha256WithRsa 算法请求类型
     */
    public static final String SIGN_TYPE_RSA2                 = "RSA2";

    public static final String SIGN_ALGORITHMS                = "SHA1WithRSA";

    public static final String SIGN_SHA256RSA_ALGORITHMS      = "SHA256WithRSA";

    public static final String ENCRYPT_TYPE_AES               = "AES";

    public static final String APP_ID                         = "appId";


    public static final String TRADE_TYPE                      = "apiType";
    public static final String DATA                      = "data";
    public static final String MERCHANTNO                      = "merchantNo";


    public static final String TIMESTAMP                      = "timestamp";

    public static final String VERSION                        = "version";

    public static final String SIGN                           = "sign";

    public static final String ACCESS_TOKEN                   = "auth_token";

    public static final String APP_AUTH_TOKEN                 = "app_auth_token";

    public static final String TERMINAL_TYPE                  = "terminal_type";

    public static final String TERMINAL_INFO                  = "terminal_info";

    public static final String CHARSET                        = "charset";

    public static final String NOTIFY_URL                     = "notify_url";

    public static final String RETURN_URL                     = "return_url";

    public static final String ENCRYPT_TYPE                   = "encryptType";

    //-----===-------///

    public static final String BIZ_CONTENT_KEY                = "biz_content";

    /** 默认时间格式 **/
    public static final String DATE_TIME_FORMAT               = "yyyy-MM-dd HH:mm:ss";

    /**  Date默认时区 **/
    public static final String DATE_TIMEZONE                  = "GMT+8";

    /** UTF-8字符集 **/
    public static final String CHARSET_UTF8                   = "UTF-8";

    /** GBK字符集 **/
    public static final String CHARSET_GBK                    = "GBK";

    /** JSON 应格式 */
    public static final String FORMAT_JSON                    = "json";

    /** XML 应格式 */
    public static final String FORMAT_XML                     = "xml";

    /** SDK版本号 */
    public static final String SDK_VERSION                    = "open-sdk-java-1.0.0";

    public static final String PROD_CODE                      = "prod_code";

    /** 老版本失败节点 */
    public static final String ERROR_RESPONSE                 = "error_response";

    /** 新版本节点后缀 */
    public static final String RESPONSE_SUFFIX                = "_response";

    /** 加密后XML返回报文的节点名字 */
    public static final String RESPONSE_XML_ENCRYPT_NODE_NAME = "response_encrypted";

}
