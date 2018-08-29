package com.zz.opensdk.sdk.utils;

import com.zz.opensdk.sdk.common.OpenApiConstants;
import com.zz.opensdk.sdk.common.OpenApiRuntimeException;
import com.zz.opensdk.sdk.domain.OpenAPIEntity;

/**
 * @author zhangzuizui
 * @date 2018/7/11 20:19
 */
public class OpenApiHelper {


    private static final String SIGNTYPE = OpenApiConstants.SIGN_TYPE_RSA2;
    private static final String ENCRYPTTYPE = OpenApiConstants.ENCRYPT_TYPE_AES;


    public static String decodeBizEntity(OpenAPIEntity openAPIEntity, String merPublicKey, String signType, String aesPrivateKey) throws OpenApiRuntimeException {
        String data = null;
        boolean checkSignRes = signCheck(openAPIEntity, merPublicKey, signType);
        if(!checkSignRes){
            throw new OpenApiRuntimeException("验签失败");
        }
        //解密
        if(ENCRYPTTYPE.equals(openAPIEntity.getEncryptType())){
            data = EncryptUtils.decryptContent(openAPIEntity.getData(), "AES",
                    aesPrivateKey, openAPIEntity.getCharset());
        }else {
            data = openAPIEntity.getData();
        }
        return data;
    }

    public static boolean signCheck(OpenAPIEntity openAPIEntity, String merPublicKey, String signType) throws OpenApiRuntimeException {
        String sourceContent = SignUtils.getSignString(openAPIEntity);
        //验签,暂时只支持一种 RSA2
        return SignUtils.rsaCheck(sourceContent, openAPIEntity.getSign(), merPublicKey, openAPIEntity.getCharset(), signType);

    }


    public static String encodeBizEntity(String object, String aesPrivateKey, String charset) throws OpenApiRuntimeException{
        return EncryptUtils.encryptContent(object, "AES", aesPrivateKey, charset);
    }

    public static String sign(OpenAPIEntity openAPIEntity, String privateKey) throws OpenApiRuntimeException{
        String sourceContent = SignUtils.getSignString(openAPIEntity);
        String sign = SignUtils.rsaSign(sourceContent, privateKey, openAPIEntity.getCharset(), openAPIEntity.getSignType());
        return sign;
    }
}
