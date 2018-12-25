package com.springboot.service.hengshui;


import com.springboot.model.SendMsgCodeModel;
import com.springboot.model.hsbank.HsParam;
import com.springboot.model.hsbank.req.ReqBody1006;
import com.springboot.model.hsbank.resp.RespBody;
import com.springboot.model.hssocket.req.Req2004;

/**
 *
 * @version 创建时间：2018年5月8日 下午1:48:30
 * 账户拼单关系业务接口
 */

public interface HSBankService {

    RespBody addProduct(String prodId);

    boolean auth(SendMsgCodeModel sendMsgCodeModel);

    /**
     * 发送短信
     *
     * @param mobilePhone
     * @param text
     * @return
     */
    boolean sendSms(String mobilePhone, String text);

    HsParam payProdForHS(Req2004 req2004);


    String queryState(ReqBody1006 reqBody1006);

}
