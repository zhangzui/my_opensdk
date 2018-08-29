package com.zz.opensdk.sdk.test;


import com.zz.opensdk.sdk.domain.ResponseBaseVo;
import com.zz.opensdk.sdk.handle.DefaultResquestHandle;
import com.zz.opensdk.sdk.request.ReceiveOrderRequest;

/**
 * @author zhangzuizui
 * @date 2018/7/11 12:08
 */
public class ReceiveOrderTest {

    /**
     * this example is a ReceiveOrder demo
     * 1.merchant base on our develpe ducument build request params
     * 2.OpenAPIEntity is common of request and response params
     *
     * demo
     * 1.handle is lead you build request and do http post,than get jv service response
     * 2.first merchant must encrypt data (the service request body ,like ReceiveOrderRequest)
     * 3.than connect the OpenAPIEntity flieds to get sign
     * 4.do http post
     * 5.check sign and decrypt data (get Object from response string data)
     */
    public static void main(String[] args) {
        //merchant RSA private key
        String merPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCGZO4dpE6bQJGw6isZP3ho40JcVKO1tOLMkn56FPb4tQfg55EInk0fnSKPk8Z8mEuNkSypIFFm1Q+oRBSUWeLUvOJLfV6TM7rIIY8KcvaROy+mTjijXD6Dr0WUzJc6YetUV0A6kwZq9rO4/KKkEdeyMiDSRaW6SKLYfJ2NS4wKA9IvqOXM+B/YwPgopGa77jhla21C9kDuKH6nNmQwUWkzU2xvi0C9Vi1YmP2HQB8LR4aFaw5aaKEAdJkJmrVFIocoJdh7kPMAtWqfmbd8OX8DHzs1YvKIO9x7On760Ti68VZkOQt7vizUWDK5U5SaNGE3og88LNbLN5AgBtYPf8F1AgMBAAECggEAK5IHPKbrMRwi36dbZ6kTIXYkKojqNlu5d7c6t9uaIhGWx0xr0c54lHo+HSw8mmJJhFD/mKk4ms0ScWv4/AuUJYJDH1Q62CBaOs3ecPXKiFUW4qTTaera/ZhSR6Z4lN3wDHzo/tRxrTXkGGIXXHfIZSYpS+J2k0k/Ok5USd2nPgt8FxcQB8bh5EuhiLQRT0EXQqpyoWK41dowdf9+u8h8/7FfAJzKtEJ6L05UoxDuQjZKXZ+K36ueyf//DPIf8PRaG2CUDrjSc5+eySGqOjsZ4TK6Y7ne1l4nM3Z802KZins1Wk2zLztaWtz64ZL1aaG98IrfFyM3VVrpstA070dcNQKBgQDPBLQzFOYG9gxMp03DrExIHQg85kw+9S/sd2MGhbAshivKHtYZseF4kclzwhtCkcOxVRcYUq7qmwXXVQ26+LuXIqIwGAQaVnkHMVW+iIm5NMnBq6FNr+8EcQlSXDX7CSjQwGxS1vSbDW4JH83HT4lbkV/Ge+IjaJl+SNj7KSLRIwKBgQCmMVDVYp83XeXkJHWXDn7DFz58HVHLy126iS4GJBc4mgqA+f7EQZ/63en8Ypo2VSUrtkNaFJyVyOjoCHfbjI7nD36Uew8LDCrXKXxbOQwgBgQUC8Jog4+1Kcy7e7VEZ5cNRD2HJfGQcvR0iUt/hZi/z7iSP5t7Y0PfPTJ18xIohwKBgQCQgkQX9FquOUtUpuUN1lRZcoqxmEFZIWaWtrAVeXUFX5NpwCTMQvOVao8pkzHcQkxMmY1JmU4ozpZBPkobXk30rmRxFhKnH/3UG4gzPLSG6GZ+HwS2aj166D0NlPjJiBdJwUjceti8858AF4dZvhewJ8ankwrElNuw38E3cfuv6QKBgQCTH3tAvALY/94BHqmepS1gxdYCB+6ebBU4ijJdK1wHNo7v6OA5myLl8cgvLpb4XF4AmWR8fVy1WNuedcitanwjXtse1OXXUNkoCsjU9Oaf/tNjb5mV7kmtBikBHB23RcE59FxHpTZ5Otrfw587b+hufZQWnAqnq5OtL77ZHerIRwKBgGp8SG6UcdTAVp65PhHZsVYheLg0yWAzxXExQPDq6/dt7HtTAU6+G2YAkJwJNWB7OYD+g9bcej86JwyDwv23gItjS8JYzopfgbtGh4Ne1ErRIRxn46MrCHaluzmC+XtuI2WKaDY7mJNGjIQPWjtVVftmPPGghnR9tNA415B/5fDU";

        //jv RSA public key
        String jVPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtbA2192l97C2sqnfCw3NAI8taezavX86Ec7jK1T33SkVDOuL+ikaRSW+jN2d2usCDZ/rvzrAEjYf5oA68TReGgINeXrS6PAKdGPJvi2jIb24oW92GbAbd794LsWa3DqAM75XkPl7B5Pz1Iw84CRaFXvwjgDRt/LpP0B6WSfkPh7+kgCb8BdGwkEqzP5HUmyM5elAiobayAKYvm06RxY1ZlG6f34Q/9PMr+jDtx8t+iTQlfDPgNeOHz05xFfiudmZh8CcSwAWQ2xepJTY04oO25ii65CbthoJ5Sqie5QlVcUXHgojwW0dVc0thVwIo9fmb9Qiv6ndoefnhZdcWWzn4wIDAQAB";

        String apiType = "test";
        String appId = "20180723000014";
        String chartset = "utf-8";
        String encryptKey = "3in3iv837k978qcchv9reneh";
        String merchantNo = "360087641000000252";
        String version = "1.0.0";
        String url = "http://59.151.64.31:8237/open_api/gateway";

        // 1.new DefaultResquestHandle for test
        DefaultResquestHandle defaultResquestHandle = new DefaultResquestHandle(
                version,apiType,appId,merchantNo,chartset,
                merPrivateKey,jVPublicKey,encryptKey,url);

        // 2.new Request bean (example：this is a save order interface)
        ReceiveOrderRequest receiveOrderRequest = new ReceiveOrderRequest();
        receiveOrderRequest.setOrderId("12021231212");

        // 3.do handle
        ResponseBaseVo responseBaseVo = defaultResquestHandle.handle(receiveOrderRequest);
    }
}
