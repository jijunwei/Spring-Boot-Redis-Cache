package com.springboot.constant;

import com.google.gson.Gson;

import java.time.format.DateTimeFormatter;

/**
 * @author
 * @time 2017-09-23 10:19
 */
public interface Constants {

    Gson GSON = new Gson();
    String PASSWORD_SALT = "_dx";
    String ZERO_FILL_TEMPLATE = "%04d";
    String FLOAT_MONEY_ZERO_FILL = "%.2f";
    String ROLE_ = "ROLE_";
    String PRE_TOKEN = "token:";

    String PRODUCT_TYPE_01 = "01";
    Boolean IS = true;
    Boolean IS_NOT = false;
    int PUBLISHTYPE=2; //产品发布
    int UNPUBLISHTYPE=3;// 取消发布
    int ENDTYPE=4;  //拼单结束
    int SUCCESSTYPE=5;//拼单成功
    int FAILTYPE=0; //拼单失败时
    int EDITTYPE=6; //编辑产品


    // JDK1.8 提供的线程安全的日期转换类
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
    DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("y年M月d日");
    String LAST_TIME_PER_DAY = " 23:59:59";
    String APP_NAME = "拼财富";

    // token
    long TOKEN_EXPIRE = 24 * 60 * 60 * 1000; // token过期时间

    /**
     * 衡水需要的永久有效token过期时间  初步定为10年
     */
    long HS_TOKEN_EXPIRE = 365 * 10;

    // ====发送短信息相关
    int VERIFY_CODE_SIZE = 6; // 短信验证码长度

    int MESSAGE_TYPE_REGIST = 1; // 注册时发送的短信息

    int MESSAGE_TYPE_MODIFY_PHONE = 2; // 修改手机号时发送的短信息

    int MESSAGE_TYPE_SET_PASSWORD = 3; // 设置密码时发送的短信息

    int MESSAGE_TYPE_FORGET_PASSWORD = 4; // 找回密码时发送的短信息

    int MESSAGE_TYPE_MODIFY_PASSWORD = 5; // 修改密码时发送的短信息

    int MESSAGE_TYPE_SET_TRANSCTION_SECRET = 6; // 设置交易密码时发送的短信息

    int MESSAGE_TYPE_MODIFY_TRANSCTION_SECRET = 7; // 修改交易密码时发送的短信息

    int MESSAGE_TYPE_LOGIN = 8; // 短信验证码登录时时发送的短信息

    // 短信的发送频率
    int MESSAGE_SEND_RATE = 5;

    // 短信服务状态
    String MESSAGE_SERVICE_STATUS_FAIL = "1";
    String MESSAGE_SERVICE_STATUS_SUCCESS = "0";

    // ===APP中的图片类型
    // 主页图片
    int APP_PICTURE_TYPE_HOME = 1;

    Integer TYPE_INCOME = 1;
    Integer TYPE_PAY = 2;
    Integer TYPE_V1 = 3;
    Integer TYPE_V2 = 4;
    Integer TYPE_V3 = 5;
    Integer Unreleased=0;
    Integer Recruitment_Period=1;
    Integer Collage_Success=2;
    Integer Collage_Failure=3;
    Integer Collage_End=4;


}
