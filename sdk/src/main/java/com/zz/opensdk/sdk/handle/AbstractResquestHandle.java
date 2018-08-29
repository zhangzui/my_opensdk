package com.zz.opensdk.sdk.handle;

import com.alibaba.fastjson.JSON;
import com.zz.opensdk.sdk.common.OpenApiConstants;
import com.zz.opensdk.sdk.common.OpenApiRuntimeException;
import com.zz.opensdk.sdk.domain.OpenAPIEntity;
import com.zz.opensdk.sdk.domain.RequestBaseVo;
import com.zz.opensdk.sdk.domain.ResponseBaseVo;
import com.zz.opensdk.sdk.utils.SignUtils;
import com.zz.opensdk.sdk.utils.WebUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author zhangzuizui
 * @date 2018/7/11 14:37
 */
public abstract class AbstractResquestHandle implements RequestHandle{

    private static final String SIGNTYPE = OpenApiConstants.SIGN_TYPE_RSA2;
    private static final String ENCRYPTTYPE = OpenApiConstants.ENCRYPT_TYPE_AES;

    private String version;
    private String tradeType;
    private String appId;
    private String charset;
    private String merchantNo;
    private String url;

    public AbstractResquestHandle(String version, String tradeType, String appId, String charset, String merchantNo, String url) {
        this.version = version;
        this.tradeType = tradeType;
        this.appId = appId;
        this.charset = charset;
        this.merchantNo = merchantNo;
        this.url = url;
    }

    public ResponseBaseVo handle(RequestBaseVo requestBaseVo) {
        //1.build request params
        OpenAPIEntity openAPIEntity = null;
        try {
            openAPIEntity = buildBizEntity(requestBaseVo);
        } catch (OpenApiRuntimeException e) {
           // TODO: 2018/7/16 throw error
        }

        Map<String,String> req = SignUtils.parmasToMap(openAPIEntity);

        //2.http dopost
        long start = System.currentTimeMillis();
        String res = doPost(openAPIEntity);

        long end = System.currentTimeMillis();
        long time = end-start;

        //3.get the response
        ResponseBaseVo responseBaseVo = handleResponse(JSON.parseObject(res,Map.class),time);
        return responseBaseVo;
    }

    private String doPost(OpenAPIEntity openAPIEntity) {
        String contentType = "application/json;charset=" + charset;
        try {
            String rsp = WebUtils.doPost(this.url,contentType, JSON.toJSONString(openAPIEntity).getBytes(),
                    3000, 10000, null, 0);
            return rsp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private OpenAPIEntity buildBizEntity(RequestBaseVo requestBaseVo) throws OpenApiRuntimeException {
        OpenAPIEntity openAPIEntity = new OpenAPIEntity();
        openAPIEntity.setMerchantNo(this.merchantNo);
        openAPIEntity.setVersion(this.version);
        openAPIEntity.setEncryptType(ENCRYPTTYPE);
        openAPIEntity.setAppId(this.appId);
        openAPIEntity.setCharset(this.charset);
        openAPIEntity.setSignType(SIGNTYPE);
        openAPIEntity.setTimestamp(getDate());
        openAPIEntity.setApiType(this.tradeType);

        // encrypt
        if (openAPIEntity.getEncryptType().equals(ENCRYPTTYPE)) {
            openAPIEntity.setData(getEncryptor().encrypt(JSON.toJSONString(requestBaseVo), ENCRYPTTYPE, charset));
        } else {
            openAPIEntity.setData(JSON.toJSONString(requestBaseVo));
        }

        // sign
        openAPIEntity.setSign(getSigner().sign(SignUtils.getSignString(openAPIEntity), SIGNTYPE, charset));
        return openAPIEntity;
    }
    
    private ResponseBaseVo handleResponse(Map res, long consumingTime) {
        //get response sign
        String signature = res.get(OpenApiConstants.SIGN).toString();
        //System.out.println("Map-res:"+JSON.toJSONString(res));
        res.remove(OpenApiConstants.SIGN);
        String sourceContent = SignUtils.getSignContent(res);
        //System.out.println("sourceContent:"+sourceContent);
        //check jv sign
        if(getSignChecker().check(sourceContent,signature,SIGNTYPE,this.charset)){
            String data = getDecryptor().decrypt(res.get("data").toString(),ENCRYPTTYPE,this.charset);
            System.out.println("check sign success*****doPost time consuming:"+consumingTime+"ms ,success-res："+data);
            return JSON.parseObject(data,ResponseBaseVo.class);
        }
        System.out.println("check sign fail");
        return null;
    }


    public abstract Signer getSigner();

    public abstract SignChecker getSignChecker();

    public abstract Encryptor getEncryptor();

    public abstract Decryptor getDecryptor();

    public String getDate() {
        String fromat = "yyyy-MM-dd HH:MM:SS";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fromat);
        String date = simpleDateFormat.format(new Date());
        return date;
    }
}
