package com.springboot.constant;

import java.util.HashMap;
import java.util.Map;

public class HsBankConstants {

    public static final String SERVER_BUY = "2001";

    public static final String SERVER_QX_BUY = "2002";

    public static final String SERVER_VICTORY = "2003";

    public static final String SERVER_TIXIAN = "2004";

    public static final String SERVER_SENTSMS = "2005";

    public static final String SERVER_QUERY = "2006";

    public static final Integer PAY_STATUS_0 = 0;    //支付中
    public static final Integer PAY_STATUS_1 = 1;    //支付成功
    public static final Integer PAY_STATUS_2 = 2;    //支付失败
    public static final Integer PAY_STATUS_3 = 3;    //提现中
    public static final Integer PAY_STATUS_4 = 4;    //提现成功
    public static final Integer PAY_STATUS_5 = 5;    //提现失败
    public static final Integer PAY_STATUS_6 = 6;    //取消购买中
    public static final Integer PAY_STATUS_7 = 7;    //取消购买成功
    public static final Integer PAY_STATUS_8 = 8;    //取消购买失败
    public static final Integer PAY_STATUS_9 = 9;    //
    public static final Integer PAY_STATUS_10 = 10;    //
    public static final Integer PAY_STATUS_11 = 11;    //

    public static final Integer ORDER_STATUS_0 = 0;//拼单中
    public static final Integer ORDER_STATUS_1 = 1;//拼单失败
    public static final Integer ORDER_STATUS_2 = 2;//拼单成功
    public static final Integer ORDER_STATUS_3 = 3;//拼单结束


    public static final Map<Integer, String> proNumMap = new HashMap<>();
    public static final Map<Integer, Integer> unitsMap = new HashMap<>();
    public static final Map<Integer, Long> valueMap = new HashMap<>();

    static {
        proNumMap.put(0, "2I1");
        proNumMap.put(1, "2I2");
        proNumMap.put(2, "2I3");
        proNumMap.put(3, "2I4");
        proNumMap.put(4, "2I5");
        proNumMap.put(5, "2I6");

        unitsMap.put(0, 1);
        unitsMap.put(1, 1);
        unitsMap.put(2, 0);
        unitsMap.put(3, 0);
        unitsMap.put(4, 0);
        unitsMap.put(5, 0);

        valueMap.put(0, 3l);
        valueMap.put(1, 6l);
        valueMap.put(2, 1l);
        valueMap.put(3, 2l);
        valueMap.put(4, 3l);
        valueMap.put(5, 5l);
    }


}
