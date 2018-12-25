package com.springboot.util;

import com.springboot.constant.Constants;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

public class ProductUtils {

    public int bits = 4;
    public Integer year = 0;
    public Integer month = 1;
    public Integer day = 2;

    public String buildProductNumber(String bankNumber, String productType, Long proNums) {

        String sub = countLastSubStr(proNums);

        return bankNumber + productType + sub;

    }


    public String countLastSubStr(Long proNums) {

        if (proNums == null) {
            proNums = 0L;
        }
        Long l = 10001L + proNums;
        String str = String.valueOf(l);

        return str.substring(str.length() - bits, str.length());

    }

    /**
     * 获取订单编码
     */
    public static String getOrderNumber(String bankNum, String cityNum) {
    	
    	String orderNum = ""; 
    	if(StringUtils.isEmpty(bankNum) && StringUtils.isEmpty(cityNum)) {
    		return orderNum;
    	}
    	//添加号
    	if(bankNum.length()>=4) {
    		orderNum += bankNum.substring(0, 4);
    	} else {
    		for(int i=0;i<4-bankNum.length();i++) {
    			orderNum +="0";
    		}
			orderNum += bankNum;
		}
    	//添加地区号
    	if(cityNum.length()>=4) {
    		orderNum += cityNum.substring(0, 4);
    	} else {
    		for(int i=0;i<4-cityNum.length();i++) {
    			orderNum +="0";
    		}
			orderNum += cityNum;
		}
    	//添加当前时间
    	LocalDateTime localDateTime = LocalDateTime.now(); 
    	String nowTime = localDateTime.format(Constants.formatter1);
    	nowTime = nowTime.replace(":", "").replace(" ", "").replace("-", "");
    	orderNum +=nowTime; 
    	//添加四位随机数
    	orderNum += VerifyCodeUtils.generateVerifyCode(4); 
    	return orderNum;
    }

}
