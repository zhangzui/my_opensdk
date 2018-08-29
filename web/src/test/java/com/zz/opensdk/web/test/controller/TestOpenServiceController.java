package com.zz.opensdk.web.test.controller;

import com.alibaba.fastjson.JSON;
import com.zz.opensdk.sdk.common.OpenApiConstants;
import com.zz.opensdk.sdk.common.OpenApiRuntimeException;
import com.zz.opensdk.sdk.domain.OpenAPIEntity;
import com.zz.opensdk.sdk.domain.ResponseBaseVo;
import com.zz.opensdk.sdk.utils.EncryptUtils;
import com.zz.opensdk.sdk.utils.OpenApiHelper;
import com.zz.opensdk.web.controller.OpenServiceController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @author zhangzuizui
 * @date 2018/2/28 11:30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring-config.xml"})
public class TestOpenServiceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestOpenServiceController.class);

    @Autowired
    private OpenServiceController serviceController;

//    public static final String PRIVATEKEY = "012345678901234567890123";
    public static final String PRIVATEKEY = "3in3iv837k978qcchv9reneh";
    public static final String CHARSET = "UTF-8";

    private String signType       = OpenApiConstants.SIGN_TYPE_RSA2;
    private String encryptType       = OpenApiConstants.ENCRYPT_TYPE_AES;

    String merPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhmTuHaROm0CRsOorGT94aONCXFSjtbTizJJ+ehT2+LUH4OeRCJ5NH50ij5PGfJhLjZEsqSBRZtUPqEQUlFni1LziS31ekzO6yCGPCnL2kTsvpk44o1w+g69FlMyXOmHrVFdAOpMGavazuPyipBHXsjIg0kWlukii2HydjUuMCgPSL6jlzPgf2MD4KKRmu+44ZWttQvZA7ih+pzZkMFFpM1Nsb4tAvVYtWJj9h0AfC0eGhWsOWmihAHSZCZq1RSKHKCXYe5DzALVqn5m3fDl/Ax87NWLyiDvcezp++tE4uvFWZDkLe74s1FgyuVOUmjRhN6IPPCzWyzeQIAbWD3/BdQIDAQAB";
    String merPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCGZO4dpE6bQJGw6isZP3ho40JcVKO1tOLMkn56FPb4tQfg55EInk0fnSKPk8Z8mEuNkSypIFFm1Q+oRBSUWeLUvOJLfV6TM7rIIY8KcvaROy+mTjijXD6Dr0WUzJc6YetUV0A6kwZq9rO4/KKkEdeyMiDSRaW6SKLYfJ2NS4wKA9IvqOXM+B/YwPgopGa77jhla21C9kDuKH6nNmQwUWkzU2xvi0C9Vi1YmP2HQB8LR4aFaw5aaKEAdJkJmrVFIocoJdh7kPMAtWqfmbd8OX8DHzs1YvKIO9x7On760Ti68VZkOQt7vizUWDK5U5SaNGE3og88LNbLN5AgBtYPf8F1AgMBAAECggEAK5IHPKbrMRwi36dbZ6kTIXYkKojqNlu5d7c6t9uaIhGWx0xr0c54lHo+HSw8mmJJhFD/mKk4ms0ScWv4/AuUJYJDH1Q62CBaOs3ecPXKiFUW4qTTaera/ZhSR6Z4lN3wDHzo/tRxrTXkGGIXXHfIZSYpS+J2k0k/Ok5USd2nPgt8FxcQB8bh5EuhiLQRT0EXQqpyoWK41dowdf9+u8h8/7FfAJzKtEJ6L05UoxDuQjZKXZ+K36ueyf//DPIf8PRaG2CUDrjSc5+eySGqOjsZ4TK6Y7ne1l4nM3Z802KZins1Wk2zLztaWtz64ZL1aaG98IrfFyM3VVrpstA070dcNQKBgQDPBLQzFOYG9gxMp03DrExIHQg85kw+9S/sd2MGhbAshivKHtYZseF4kclzwhtCkcOxVRcYUq7qmwXXVQ26+LuXIqIwGAQaVnkHMVW+iIm5NMnBq6FNr+8EcQlSXDX7CSjQwGxS1vSbDW4JH83HT4lbkV/Ge+IjaJl+SNj7KSLRIwKBgQCmMVDVYp83XeXkJHWXDn7DFz58HVHLy126iS4GJBc4mgqA+f7EQZ/63en8Ypo2VSUrtkNaFJyVyOjoCHfbjI7nD36Uew8LDCrXKXxbOQwgBgQUC8Jog4+1Kcy7e7VEZ5cNRD2HJfGQcvR0iUt/hZi/z7iSP5t7Y0PfPTJ18xIohwKBgQCQgkQX9FquOUtUpuUN1lRZcoqxmEFZIWaWtrAVeXUFX5NpwCTMQvOVao8pkzHcQkxMmY1JmU4ozpZBPkobXk30rmRxFhKnH/3UG4gzPLSG6GZ+HwS2aj166D0NlPjJiBdJwUjceti8858AF4dZvhewJ8ankwrElNuw38E3cfuv6QKBgQCTH3tAvALY/94BHqmepS1gxdYCB+6ebBU4ijJdK1wHNo7v6OA5myLl8cgvLpb4XF4AmWR8fVy1WNuedcitanwjXtse1OXXUNkoCsjU9Oaf/tNjb5mV7kmtBikBHB23RcE59FxHpTZ5Otrfw587b+hufZQWnAqnq5OtL77ZHerIRwKBgGp8SG6UcdTAVp65PhHZsVYheLg0yWAzxXExQPDq6/dt7HtTAU6+G2YAkJwJNWB7OYD+g9bcej86JwyDwv23gItjS8JYzopfgbtGh4Ne1ErRIRxn46MrCHaluzmC+XtuI2WKaDY7mJNGjIQPWjtVVftmPPGghnR9tNA415B/5fDU";

    String JDPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtbA2192l97C2sqnfCw3NAI8taezavX86Ec7jK1T33SkVDOuL+ikaRSW+jN2d2usCDZ/rvzrAEjYf5oA68TReGgINeXrS6PAKdGPJvi2jIb24oW92GbAbd794LsWa3DqAM75XkPl7B5Pz1Iw84CRaFXvwjgDRt/LpP0B6WSfkPh7+kgCb8BdGwkEqzP5HUmyM5elAiobayAKYvm06RxY1ZlG6f34Q/9PMr+jDtx8t+iTQlfDPgNeOHz05xFfiudmZh8CcSwAWQ2xepJTY04oO25ii65CbthoJ5Sqie5QlVcUXHgojwW0dVc0thVwIo9fmb9Qiv6ndoefnhZdcWWzn4wIDAQAB";
    String JDPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC1sDbX3aX3sLayqd8LDc0Ajy1p7Nq9fzoRzuMrVPfdKRUM64v6KRpFJb6M3Z3a6wINn+u/OsASNh/mgDrxNF4aAg15etLo8Ap0Y8m+LaMhvbihb3YZsBt3v3guxZrcOoAzvleQ+XsHk/PUjDzgJFoVe/COANG38uk/QHpZJ+Q+Hv6SAJvwF0bCQSrM/kdSbIzl6UCKhtrIApi+bTpHFjVmUbp/fhD/08yv6MO3Hy36JNCV8M+A144fPTnEV+K52ZmHwJxLABZDbF6klNjTig7bmKLrkJu2GgnlKqJ7lCVVxRceCiPBbR1VzS2FXAij1+Zv1CK/qd2h5+eFl1xZbOfjAgMBAAECggEBAJOhb67Px4AP8yQRq87Lk/wUWw5rCwJXUtQNi8E19YGbVCN/cen/Y/0zwjWsDgxIUQ7JF0H3dwJVQG6HW12K87umV1SSXLYx9gzE2LTTWG6ePlvOueP9mRQgXIOExWSVlH5296gxT6rEqafXm891Ok2iu8BnIgGaO7TNq5yuMWPXY7x4GP7Vbek4QAWuIRDTDfqPoSkZPofaQQmttx+7emlRv3PbIpMRwKX/X9qkU3mfmwIuYVxbDbUB+08WYMcTijyhwmrSsbJwnDN8D5NWNlgS6pEu4ML+hiAT+zTzcD+5AeFsynfub7dOKUALjtRr3OktfverPJNaglK1XuIirgECgYEA6r8Q+hi1x95wTWqBTFOaU8aRfkGRwrUkh/6RvvB4BfNHoEXYxHk3mJsjKpL2wPuF4T6EjhYdsAeBlnArVuO0jM9a9vWm52/3yxxiSFlaxAyDa/OTkMgDbkJOTRAOZpVVatU1APiWJYSVEuKNLHWDwQNKN44/GPSek/wT1YElUqMCgYEAxiNfhUWNn5YUn3nrHOHrPwmMWjtLAy91KklLmqftHIYdO15rQM3395Y+KDx2gYDABorueo2k/mq6bM77zPCkJjLFwjGdbN+iu7OdtvVDVx+5f1QDVy89nm5frGPHj7OarmhCeW2rTMiEuvCzwIxsj59emCe77x9vpG3YIMwUqcECgYB7QLq/Nb4sJQS+AQ/GbZb4Kag2rGLF4qo9pVIM+OFr7mJLKqE395VTURFTxzo79VREAblDiSUpSaemv1piEsi3rh/KzwxFNCrcwBhyeEgJ67ANPCTcvcffWn0WRTnqbrocj7l2uw7KwCQteDo7f7Af5YRQSyWiCr5AYPhg/6ADFQKBgF47upVGxBlvJnXGZUSCSgDcCK1tX7qvGnk3DMcMMKANRN/Ugwv6KYHOKde3EeF7m1hBQTR2I0+A8RMIygbpOBiWsyktsaJzJhFBVrQKJ7HK+80L5t7TL+omskfp2KKGl/10xpC70OmewEmsasEbyBHyiOmylBYhDFbWiyDmDrnBAoGAFFZnI1e8a8hN07XE3Xf/ItfFF6wnb2LrLHUmw5/T8gaWiJ0EMIi7ZMBYSIKruAfqnkCJ96lqYQVnoAT1hdpoBpBzYb2eeaBVCNrOSOwXHtJ79ss6DOgpWjS1ynHvvlcUgOt6HIOyQLU5Nd4duHQByeCqVr/4i4AhzPNGWWqGOMQ=";

    @Test
    public void testOpenSdk() throws OpenApiRuntimeException {
        OpenAPIEntity req = buildBizEntity();

        LOGGER.info("请求参数：" + JSON.toJSONString(req));

        OpenAPIEntity res = serviceController.gateway(req);

        LOGGER.info("返回结果："+JSON.toJSONString(res));

        getResponse(res);

    }

    private void getResponse(OpenAPIEntity res) throws OpenApiRuntimeException {
        //验签
        OpenApiHelper.signCheck(res,JDPublicKey,res.getSignType());
        //解密
        //6.解密
        if(res.getEncryptType().equals("AES")){
            ResponseBaseVo responseBaseVo = JSON.parseObject(EncryptUtils.decryptContent(res.getData(),"AES",PRIVATEKEY, res.getCharset()),ResponseBaseVo.class);
            System.out.println("aes解密："+JSON.toJSONString(responseBaseVo));
        }else {
            System.out.println("直接反序列化res.getData()："+res.getData());
            ResponseBaseVo responseBaseVo =  JSON.parseObject(res.getData(), ResponseBaseVo.class);
            System.out.println("直接反序列化："+JSON.toJSONString(responseBaseVo));
        }
    }

    private OpenAPIEntity buildBizEntity() throws OpenApiRuntimeException {

        String data = "{\"merchantNo\":\"360087641000000005\",\"storeNo\":\"36008764100000000501\",\"device_info\":\"000001\",\"body\":\"product description\",\"attach\":\"attach message\",\"out_trade_no\":\"merchant_order_id_0001\",\"total_fee\":\"0.1\",\"fee_type\":\"THB\",\"spbill_create_ip\":\"127.0.0.1\",\"time_start\":\"20180713091010\",\"time_expire\":\"20180714091010\",\"auth_code\":\"1231321231231\"}";

        OpenAPIEntity openAPIEntity = new OpenAPIEntity();
        openAPIEntity.setMerchantNo("360087641000000252");
        openAPIEntity.setVersion("1.0.0");
        openAPIEntity.setEncryptType("AES");
        openAPIEntity.setAppId("20180723000014");
        openAPIEntity.setCharset(CHARSET);
        openAPIEntity.setSignType("RSA2");
        openAPIEntity.setTimestamp(String.valueOf(new Date().getTime()));
        openAPIEntity.setApiType("test");
        //6.data加密
        if(openAPIEntity.getEncryptType().equals("AES")){
            openAPIEntity.setData(EncryptUtils.encryptContent(data,"AES",PRIVATEKEY, openAPIEntity.getCharset()));
        }else {
            openAPIEntity.setData(data);
        }
        //加签
        String preSign = OpenApiHelper.sign(openAPIEntity,merPrivateKey);
        openAPIEntity.setSign(preSign);

        return openAPIEntity;
    }

}
