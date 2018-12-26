package com.springboot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *   on 2017/6/29.
 */
public final class MD5Util {
    private static final Logger LOGGER = LoggerFactory.getLogger(MD5Util.class);

    public static String build(String content) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest
                    .getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
        messageDigest.update(content.getBytes());
        byte[] domain = messageDigest.digest();
        StringBuilder md5StrBuff = new StringBuilder();
        // converting domain to String
        for (int i = 0; i < domain.length; i++) {
            if (Integer.toHexString(0xFF & domain[i]).length() == 1) {
                md5StrBuff.append("0").append(
                        Integer.toHexString(0xFF & domain[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & domain[i]));
            }
        }
        return md5StrBuff.toString();
    }

    public static String getKeyedDigest(String strSrc, String key) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(strSrc.getBytes("UTF8"));
            String result = "";
            byte[] temp;
            temp = md5.digest(key.getBytes("UTF8"));
            for (int i = 0; i < temp.length; i++) {
                result += Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6);
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
