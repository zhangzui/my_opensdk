package com.zz.opensdk.sdk.common.util;

import java.math.BigDecimal;

public class BigDecimalUtils {
    /**
     * 消费金融计算都保留2位精度，采用四舍五入
     */
    private static final int DEFAULT_SCALE = 2;


    /**
     * 业务层用该方法作减法计算（保留两位小数，四舍五入）
     * @param addend
     * @param augend
     * @return
     */
    public static BigDecimal addScale(BigDecimal addend, BigDecimal augend) {
        if (addend == null && augend == null) {
            return null;
        }
        return addScale(addend, augend, DEFAULT_SCALE);
    }

    private static BigDecimal addScale(BigDecimal addend, BigDecimal augend, int scale) {
        return add(addend, augend).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 加法运算原子方法
     * @param addend
     * @param augend
     * @return
     */

    private static BigDecimal add(BigDecimal addend, BigDecimal augend) {
        if (addend == null && augend == null)
            return null;
        if (addend == null && augend != null)
            return augend;
        if (addend != null && augend == null)
            return addend;

        return addend.add(augend);
    }

    /**
     * 业务层用该方法作减法计算（保留两位小数，四舍五入）
     * @param minuend
     * @param subtrahend
     * @return
     */
    public static BigDecimal subtractScale(BigDecimal minuend, BigDecimal subtrahend) {
        if (minuend == null && subtrahend == null) {
            return null;
        }
        return subtractScale(minuend, subtrahend, DEFAULT_SCALE);
    }

    private static BigDecimal subtractScale(BigDecimal minuend, BigDecimal subtrahend, int scale) {
        return subtract(minuend, subtrahend).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }



    /**
     * 原子方法做减法运算 TIP:改了四舍五入
     * @param minuend
     * @param subtrahend
     * @return
     */
    private static BigDecimal subtract(BigDecimal minuend, BigDecimal subtrahend) {
        if (minuend == null) {
            return null;
        }

        if (subtrahend == null) {
            return minuend;
        }
        return minuend.subtract(subtrahend);
    }



    /**
     * 业务层用该方法作乘法计算（保留两位小数，四舍五入）
     * @param multiplicator
     * @param multiplicand
     * @return
     */
    public static BigDecimal multiplyScale(BigDecimal multiplicator, BigDecimal multiplicand) {
        if (multiplicator == null || multiplicand == null) {
            return null;
        }
        return multiplyScale(multiplicator, multiplicand, DEFAULT_SCALE);
    }

    private static BigDecimal multiplyScale(BigDecimal multiplicator, BigDecimal multiplicand, int scale) {
        return multiply(multiplicator, multiplicand).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 业务层用该方法作乘法计算（保留两位小数，进位处理）
     * @param multiplicator
     * @param multiplicand
     * @return
     */
    public static BigDecimal multiplyScaleUP(BigDecimal multiplicator, BigDecimal multiplicand) {
        return multiply(multiplicator, multiplicand).setScale(DEFAULT_SCALE, BigDecimal.ROUND_UP);
    }

    /**
     * 原子方法做乘法运算 TIP:改了四舍五入，保留2位小数
     * @param multiplicator
     * @param multiplicand
     * @return
     */
    private static BigDecimal multiply(BigDecimal multiplicator, BigDecimal multiplicand) {
        if (multiplicator == null) {
            return null;
        }
        if (multiplicand == null) {
            return multiplicator;
        }
        return multiplicator.multiply(multiplicand);
    }
    /**
     * 业务层用该方法作除法计算（保留两位小数，四舍五入）
     * @param dividend
     * @param divisor
     * @return
     */
    public static BigDecimal divideScale(BigDecimal dividend, BigDecimal divisor) {
        if (dividend == null || divisor == null) {
            return null;
        }
        return divideScale(dividend, divisor, DEFAULT_SCALE);
    }

    private static BigDecimal divideScale(BigDecimal dividend, BigDecimal divisor, int scale) {
        return divide(dividend, divisor).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 业务层用该方法作除法计算（保留两位小数，进位处理）
     * @param dividend
     * @param divisor
     * @return
     */
    public static BigDecimal divideScaleUP(BigDecimal dividend, BigDecimal divisor) {
        return divide(dividend, divisor).setScale(DEFAULT_SCALE, BigDecimal.ROUND_UP);
    }


    /**
     * 使用该方法做除法原子运算
     * @param dividend
     * @param divisor
     * @return
     */
    private static BigDecimal divide(BigDecimal dividend, BigDecimal divisor) {
        if (dividend == null) {
            return null;
        }
        if (divisor == null) {
            return dividend;
        }
        return dividend.divide(divisor, 4, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 四舍五入，保留2位小数
     *
     * @param value 输入项
     * @return
     */
    public static BigDecimal bigDecimalScale(BigDecimal value) {
        if (null == value) {
            return BigDecimal.ZERO;
        }
        return bigDecimalScale(value, DEFAULT_SCALE);
    }

    /**
     * 四舍五入，保留几位小数原子方法
     * @param value 输入项
     * @param scale 保留小数点的位数
     * @return
     */
    private static BigDecimal bigDecimalScale(BigDecimal value, int scale) {
        if (null == value) value = BigDecimal.ZERO;
        if (value.compareTo(BigDecimal.ZERO) == 0) {
            scale = 0;
        }
        return value.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }


}
