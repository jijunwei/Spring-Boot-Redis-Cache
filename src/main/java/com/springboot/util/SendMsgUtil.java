package com.springboot.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 *  zxy on 2018/1/26.
 */
public class SendMsgUtil {

    private static String sn="SDK-BJR-010-01101";
    private static String pwd="ab2e88#-a7#";
    private static String url="http://sdk.entinfo.cn:8061/webservice.asmx";
    
    /**
     * 发送短信服务
     * @param mobilePhone
     * @param text
     * @return
     * @throws UnsupportedEncodingException
     */
    public static Map<String,String> sendMessageByInsurance(String mobilePhone,String text) throws UnsupportedEncodingException {
        Client client=new Client(sn,pwd,url);
        String content = java.net.URLEncoder.encode(text,  "utf-8");
        String result_mt = client.mdsmssend(mobilePhone, content, "", "", "", "");

        // 处理短信返回参数信息
        Map<String,String> map = getResultInformation(result_mt);

        return map;
    }

    /**
     * 处理短信返回参数信息
     * @return
     */
    public static Map<String,String> getResultInformation(String param){
        Map<String,String> result = new HashMap<>();
        result.put("resultNum","1");
        if("-2".equals(param)){
            result.put("resultMsg","帐号/密码不正确");
        }else if("-4".equals(param)){
            result.put("resultMsg","余额不足支持本次发送");
        }else if("-5".equals(param)){
            result.put("resultMsg","数据格式错误");
        }else if("-6".equals(param)){
            result.put("resultMsg","参数有误");
        }else if("-7".equals(param)){
            result.put("resultMsg","权限受限");
        }else if("-8".equals(param)){
            result.put("resultMsg","流量控制错误");
        }else if("-9".equals(param)){
            result.put("resultMsg","扩展码权限错误");
        }else if("-10".equals(param)){
            result.put("resultMsg","内容长度长");
        }else if("-11".equals(param)){
            result.put("resultMsg","内部数据库错误");
        }else if("-12".equals(param)){
            result.put("resultMsg","序列号状态错误");
        }else if("-14".equals(param)){
            result.put("resultMsg","服务器写文件失败");
        }else if("-17".equals(param)){
            result.put("resultMsg","没有权限");
        }else if("-19".equals(param)){
            result.put("resultMsg","禁止同时使用多个接口地址");
        }else if("-20".equals(param)){
            result.put("resultMsg","相同手机号，相同内容重复提交");
        }else if("-21".equals(param) || "-22".equals(param)){
            result.put("resultMsg","Ip鉴权失败");
        }else if("-23".equals(param)){
            result.put("resultMsg","缓存无此序列号信息");
        }else if("-601".equals(param)){
            result.put("resultMsg","序列号为空，参数错误");
        }else if("-602".equals(param)){
            result.put("resultMsg","序列号格式错误，参数错误");
        }else if("-603".equals(param)){
            result.put("resultMsg","密码为空，参数错误");
        }else if("-604".equals(param)){
            result.put("resultMsg","手机号码为空，参数错误");
        }else if("-605".equals(param)){
            result.put("resultMsg","内容为空，参数错误");
        }else if("-606".equals(param)){
            result.put("resultMsg","ext长度大于9，参数错误");
        }else if("-607".equals(param)){
            result.put("resultMsg","参数错误 扩展码非数字");
        }else if("-608".equals(param)){
            result.put("resultMsg","参数错误 定时时间非日期格式");
        }else if("-609".equals(param)){
            result.put("resultMsg","rrid长度大于18,参数错误");
        }else if("-610".equals(param)){
            result.put("resultMsg","参数错误 rrid非数字");
        }else if("-611".equals(param)){
            result.put("resultMsg","参数错误 内容编码不符合规范");
        }else if("-623".equals(param)){
            result.put("resultMsg","手机个数与内容个数不匹配");
        }else if("-624".equals(param)){
            result.put("resultMsg","扩展个数与手机个数不匹配");
        }else if("-625".equals(param)){
            result.put("resultMsg","定时时间个数与手机个数不匹配");
        }else if("-626".equals(param)){
            result.put("resultMsg","rrid个数与手机个数不匹配");
        }else{
            result.put("resultMsg","短信发送成功");
            result.put("resultNum","0");
        }

        result.put("resultCode",param);

        return result;
    }



    public static void main(String[] args){
        try {
            Map<String,String> result = sendMessageByInsurance("18201599689","【海豚智保】这只是个测试");
            System.out.print(result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }


}
