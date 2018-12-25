package com.springboot.model;

import lombok.Data;

import java.io.Serializable;

/**
*
* @version 创建时间：2018年5月15日 上午10:26:26
* 发送短信验证码模型类
*/
@Data
public class SendMsgCodeModel implements Serializable{

	/** **/
	private static final long serialVersionUID = 3155866075074075946L;
	
	//用户真实姓名
	private String realName;
	//身份证号
	private String idcard;
	//卡号
	private String cardId;
	//预留手机号
	private String mobile;
	

}
