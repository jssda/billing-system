package org.team.cuc.billingsystem.utils;

import java.math.BigDecimal;

/**
 * @author xiaohao
 * @version 1.0
 * @date 2022/10/22 20:36
 */
public class BigDecimalUtil {
    public static String fen2yuan(int fen) {
        BigDecimal num1 = new BigDecimal(fen);
        return num1.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static int yuan2fen(String yuan) {
        BigDecimal num1 = new BigDecimal(yuan);
        return num1.multiply(new BigDecimal(100)).intValue();
    }

    public static int compare(String str1, String str2) {
        BigDecimal num1 = new BigDecimal(str1);
        BigDecimal num2 = new BigDecimal(str2);
        return num1.compareTo(num2);
    }
}
