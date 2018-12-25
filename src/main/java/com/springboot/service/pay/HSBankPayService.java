package com.springboot.service.pay;

/**
* @version 创建时间：2018年5月16日 上午10:08:02
* 类说明
*/
public interface HSBankPayService {

	/**
	 * 购买产品
	 */
	String payForHsBank(String reqXml);

}
