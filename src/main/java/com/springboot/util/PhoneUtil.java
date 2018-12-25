package com.springboot.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author
 * @time 2017-09-23 10:19
 */
public class PhoneUtil {
    public static boolean isPhone(String phoneNo) {
        Matcher m;
        Pattern p1, p2;
        p1 = Pattern.compile("^[0-9]{2,3}-[0-9]{1,10}-[0-9]{1,10}$");
        p2 = Pattern.compile("^[1-9]{1}[0-9]{4,8}$");
        if (phoneNo.contains("-")) {
            m = p1.matcher(phoneNo);
        } else {
            m = p2.matcher(phoneNo);
        }
        return m.matches();
    }

    public static boolean isMobile(String phoneNo) {
        return Pattern.compile("^[1][0-9]{10}$").matcher(phoneNo).matches();
    }

    public static boolean isTelphone(String phoneNo) {
        Matcher m;
        Pattern p1, p2;
        p1 = Pattern.compile("^[0-9-]+$");
        p2 = Pattern.compile("^[1-9]{1}[0-9]{4,8}$");
        if (phoneNo.contains("-")) {
            m = p1.matcher(phoneNo);
        } else {
            m = p2.matcher(phoneNo);
        }
        return m.matches();
    }

    public static boolean isKuaiDiNo(String logisticsOrderNo){
        String regEx = "^[0-9a-zA-Z]+$";
        return Pattern.compile(regEx).matcher(logisticsOrderNo).find();
    }
}
