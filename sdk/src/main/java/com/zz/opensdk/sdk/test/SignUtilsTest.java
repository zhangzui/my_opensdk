package com.zz.opensdk.sdk.test;

import com.zz.opensdk.sdk.common.OpenApiConstants;
import com.zz.opensdk.sdk.common.OpenApiRuntimeException;
import com.zz.opensdk.sdk.utils.SignUtils;

/**
 * @author zhangzuizui
 * @date 2018/7/11 16:37
 */
public class SignUtilsTest {

    private static String signType       = OpenApiConstants.SIGN_TYPE_RSA2;
    private static String chartset       = "UTF-8";

    private static String merPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhmTuHaROm0CRsOorGT94aONCXFSjtbTizJJ+ehT2+LUH4OeRCJ5NH50ij5PGfJhLjZEsqSBRZtUPqEQUlFni1LziS31ekzO6yCGPCnL2kTsvpk44o1w+g69FlMyXOmHrVFdAOpMGavazuPyipBHXsjIg0kWlukii2HydjUuMCgPSL6jlzPgf2MD4KKRmu+44ZWttQvZA7ih+pzZkMFFpM1Nsb4tAvVYtWJj9h0AfC0eGhWsOWmihAHSZCZq1RSKHKCXYe5DzALVqn5m3fDl/Ax87NWLyiDvcezp++tE4uvFWZDkLe74s1FgyuVOUmjRhN6IPPCzWyzeQIAbWD3/BdQIDAQAB";
    private static String merPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCGZO4dpE6bQJGw6isZP3ho40JcVKO1tOLMkn56FPb4tQfg55EInk0fnSKPk8Z8mEuNkSypIFFm1Q+oRBSUWeLUvOJLfV6TM7rIIY8KcvaROy+mTjijXD6Dr0WUzJc6YetUV0A6kwZq9rO4/KKkEdeyMiDSRaW6SKLYfJ2NS4wKA9IvqOXM+B/YwPgopGa77jhla21C9kDuKH6nNmQwUWkzU2xvi0C9Vi1YmP2HQB8LR4aFaw5aaKEAdJkJmrVFIocoJdh7kPMAtWqfmbd8OX8DHzs1YvKIO9x7On760Ti68VZkOQt7vizUWDK5U5SaNGE3og88LNbLN5AgBtYPf8F1AgMBAAECggEAK5IHPKbrMRwi36dbZ6kTIXYkKojqNlu5d7c6t9uaIhGWx0xr0c54lHo+HSw8mmJJhFD/mKk4ms0ScWv4/AuUJYJDH1Q62CBaOs3ecPXKiFUW4qTTaera/ZhSR6Z4lN3wDHzo/tRxrTXkGGIXXHfIZSYpS+J2k0k/Ok5USd2nPgt8FxcQB8bh5EuhiLQRT0EXQqpyoWK41dowdf9+u8h8/7FfAJzKtEJ6L05UoxDuQjZKXZ+K36ueyf//DPIf8PRaG2CUDrjSc5+eySGqOjsZ4TK6Y7ne1l4nM3Z802KZins1Wk2zLztaWtz64ZL1aaG98IrfFyM3VVrpstA070dcNQKBgQDPBLQzFOYG9gxMp03DrExIHQg85kw+9S/sd2MGhbAshivKHtYZseF4kclzwhtCkcOxVRcYUq7qmwXXVQ26+LuXIqIwGAQaVnkHMVW+iIm5NMnBq6FNr+8EcQlSXDX7CSjQwGxS1vSbDW4JH83HT4lbkV/Ge+IjaJl+SNj7KSLRIwKBgQCmMVDVYp83XeXkJHWXDn7DFz58HVHLy126iS4GJBc4mgqA+f7EQZ/63en8Ypo2VSUrtkNaFJyVyOjoCHfbjI7nD36Uew8LDCrXKXxbOQwgBgQUC8Jog4+1Kcy7e7VEZ5cNRD2HJfGQcvR0iUt/hZi/z7iSP5t7Y0PfPTJ18xIohwKBgQCQgkQX9FquOUtUpuUN1lRZcoqxmEFZIWaWtrAVeXUFX5NpwCTMQvOVao8pkzHcQkxMmY1JmU4ozpZBPkobXk30rmRxFhKnH/3UG4gzPLSG6GZ+HwS2aj166D0NlPjJiBdJwUjceti8858AF4dZvhewJ8ankwrElNuw38E3cfuv6QKBgQCTH3tAvALY/94BHqmepS1gxdYCB+6ebBU4ijJdK1wHNo7v6OA5myLl8cgvLpb4XF4AmWR8fVy1WNuedcitanwjXtse1OXXUNkoCsjU9Oaf/tNjb5mV7kmtBikBHB23RcE59FxHpTZ5Otrfw587b+hufZQWnAqnq5OtL77ZHerIRwKBgGp8SG6UcdTAVp65PhHZsVYheLg0yWAzxXExQPDq6/dt7HtTAU6+G2YAkJwJNWB7OYD+g9bcej86JwyDwv23gItjS8JYzopfgbtGh4Ne1ErRIRxn46MrCHaluzmC+XtuI2WKaDY7mJNGjIQPWjtVVftmPPGghnR9tNA415B/5fDU";

    private static String JDPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0MfPMD/ZeGZu95+rTQ0Y+nh1DIDZVhqFKtHZrz8SmfoZ6qBlHnMFqlOsC0JNw6qn2kgHP3OhHQFrO2896cwb2SSuv1gbhAd8mVyYieI3hVMNEC03x9m7Jy25dRF7boLh0Ye4Ov0/CIs7QdSw9SM0+ZUsK3wLMOX8FfAO/g3hgaZrwF9UfZixlL+b961KPvdvQPcXLcSuEUFTTeysdazix82VtppMkfSXs/rtO3gl00h9QfZwkazujVpCxitqqh7IIoLkT0Kr/CPERa5oAuuVTdYOvNlYnC63l0Fb+uSPeEbq3sllYYwFMda1M2EEAThe12OqlcR14L2ILvvlUW9RcQIDAQAB";
    private static String JDPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDQx88wP9l4Zm73n6tNDRj6eHUMgNlWGoUq0dmvPxKZ+hnqoGUecwWqU6wLQk3DqqfaSAc/c6EdAWs7bz3pzBvZJK6/WBuEB3yZXJiJ4jeFUw0QLTfH2bsnLbl1EXtuguHRh7g6/T8IiztB1LD1IzT5lSwrfAsw5fwV8A7+DeGBpmvAX1R9mLGUv5v3rUo+929A9xctxK4RQVNN7Kx1rOLHzZW2mkyR9Jez+u07eCXTSH1B9nCRrO6NWkLGK2qqHsgiguRPQqv8I8RFrmgC65VN1g682VicLreXQVv65I94RureyWVhjAUx1rUzYQQBOF7XY6qVxHXgvYgu++VRb1FxAgMBAAECggEAQj1yzfMkE2Akiuoc1mh2aVl4gYwL6Janc4t7Dm/rffDs8hULoBqMTJsZdcUGmTL26Hl72zTbhDFkDUphEWGR4FYUH19b84rQjHPlbbHZPbK47U37aav6BhcqxSrTx588+FagLA8XRaWvGlbaAhy2fQ1II0/K2SvOzEMK7q4GwzQwH1/mfIrl4zAhyL6fF+u5AmrpihtflLcJVHIVAZiQKzrlVsDt8ejn00/Mq6iyBfI0qDfwZoDw7odKaIcGRKxtEETCqCGLC7FGLwW7cZT8Pk5qkev3GTcr54TVYma/J8Xov0W+buqJwkEpoa9unFXUNWKHSq0S/nDOHFIKlYNwAQKBgQDwwt3mKJ4Fd8W+zTCiY5WiP3A+EXXbfjYHHaS7AtxM58X1FdepM4FdQfuiYpCp2w6aCCmOVi6h1CsZoE2J0z0oa+YydY7QedV3mvjooOJ76luGBo21n9velUuPcu80+DjjDmMGVgI5KXg93fQLHty1Et0mbbYWWuKX66w78RpJAQKBgQDd/r+xkuDchbNEqSiON5Ahj9o/vebdgZc1cVTqDZRx7VaNN23jHyHWiDZgMLt8VMJR52ocTUeeEv8PJcZzjQnpVF2lkITADPuBADQR4Kt4RbZ7XKV50y+7nF25brrXP+jLCmUYLq01uFSq1gJ7xjIK3lWUpt0Re+8ycABTSP0YcQKBgATDxoJ0kVQIX8h75ReBowd3+++uhseWQtl3M7hjwRpgh+Fm0kLN+yRuVt2K19QUusA/oxrnB7s6KLQ0IPS2UoKHSCH9g5sKnjfkwEJSVMkhBTiszocmp8JK8BjhrGw+8VlFAp6wexDIilGnH9pMfQ+0VN4a07yzhcJ97oWtKtkBAoGBAMzTI6N/1aEia0lKmnS9p2qQA9sUKFLwIsfYjXR2mySSy2z4W5dXPi1m+GTAEfyhZ7mSP0FlvFKJTHR5ciVjWGXExbLhKNraH6PiwChOl2cdS0V/Md65kC0WFRPmqtW3zd6o9KXfS0lpjhUh6KCzObD1exjw7MAZDZYH6QTvLzbBAoGBALUOaemB2BGY3ILkUFAay2tq2lx3Ht4BrL52K1HlIszTrrfU8vcvl1eO+6dzb2hVN/SLzw3Li0dAHKGBs3P249kpqRlmU+W9ppOPl4By297KFMgzAVUXcjEZSyZnUj5uhUhh4Q7ePNqKp7boTBcFcA8MUEhreL+dO3ru7vJ+1HYv";

    public static void main(String[] args) throws OpenApiRuntimeException {
        String merParams = "request parameters";
        //request parameters sign
        String mrechantsign = SignUtils.rsaSign(merParams, merPrivateKey, chartset, signType);
        System.out.println("mrechantsign:"+mrechantsign);

        //platform use merchant RSA public key check sgin
        boolean res = SignUtils.rsaCheck(merParams, mrechantsign,merPublicKey, chartset, signType);
        System.out.println("platform check sign result:"+res);

        //参数加签，京东自己的私钥
        String response = "success";
        String jdsign = SignUtils.rsaSign(response, JDPrivateKey, chartset, signType);
        System.out.println("jdsign:"+jdsign);

        //merchant use platfrom public key check sign
        boolean result = SignUtils.rsaCheck(response, jdsign, JDPublicKey, chartset, signType);
        System.out.println("merchant check sign result:"+result);
    }
}
