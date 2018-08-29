package com.zz.opensdk.sdk.common.config;

import com.zz.opensdk.sdk.common.exception.FrontEndSysException;

/**
 * @author zhangzuizui
 * @date 2018/6/25 11:17
 */
public enum ApiTypeEnum {

    /**
     * 收单
     */
    TEMPLATE_SERVICE("test", "测试模板"),
    //内部商家
    RECEIVE_ORDER("01", "支付收单"),
    PAY_TRANSACTION("02", "支付交易"),
    PAY_TRANSACTION_QUERY("03", "支付交易查询"),

    //对外商家
    SCAN_PAYMENT("scan_payment", "扫码支付"),
    QUERY_ORDER("query_order", "订单查询"),
    ORDER_CANCEL("order_cancel", "订单查询"),
    APPLY_REFUND("apply_refund", "订单查询"),
    QUERY_REFUND("query_refund", "订单查询"),
    ;
    /**
     * enum的值
     */
    private String code;
    /**
     * enum的显属性对象
     */
    private String name;


    ApiTypeEnum(final String code, final String name ) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public static ApiTypeEnum get(final String enumValue) {
        for (ApiTypeEnum em : ApiTypeEnum.values()) {
            if (em.getCode().equals(enumValue)) {
                return em;
            }
        }
        throw new FrontEndSysException(ExceptionConstants.front_service_000004,enumValue);
    }
}
