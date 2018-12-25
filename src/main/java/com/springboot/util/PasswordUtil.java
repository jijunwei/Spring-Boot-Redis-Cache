package com.springboot.util;

import com.springboot.constant.Constants;
import java.security.MessageDigest;

/**
 * @author
 * @time 2017-09-23 10:19
 */
public class PasswordUtil {

    private static final char[] HEX_DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    public static String MD5(String passwordInPlainText) {
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(passwordInPlainText.getBytes("utf-8"));
            byte[] md = mdTemp.digest();
            char[] str = new char[md.length << 1];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = HEX_DIGITS[byte0 >>> 4 & 0xf];
                str[k++] = HEX_DIGITS[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
     public static void main(String args[]){
         PasswordUtil pu=new PasswordUtil();
         System.out.println(pu.MD5("Aa123456!")+ Constants.PASSWORD_SALT);
     }
}
