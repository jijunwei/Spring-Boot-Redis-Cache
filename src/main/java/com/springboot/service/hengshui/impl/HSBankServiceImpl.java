package com.springboot.service.hengshui.impl;

import com.springboot.constant.HsBankConstants;
import com.springboot.controller.IBaseController;
import com.springboot.model.SendMsgCodeModel;
import com.springboot.model.hsbank.HsParam;
import com.springboot.model.hsbank.req.*;
import com.springboot.model.hsbank.resp.RespBody;
import com.springboot.model.hsbank.resp.RespMsg;
import com.springboot.model.hssocket.req.Req2004;
import com.springboot.model.hssocket.req.Req2006;
import com.springboot.model.pay.PayStatus;
import com.springboot.service.hengshui.HSBankService;
import com.springboot.util.TcpUtil;
import com.springboot.util.XmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 *
 * 账号业务类
 */
@Transactional
@Service
public class HSBankServiceImpl implements HSBankService, IBaseController {

    // 日志
    private static final Logger LOGGER = LoggerFactory.getLogger(HSBankServiceImpl.class);

    @Override
    public RespBody addProduct(String prodId) {
        ReqMsg reqMsg = new ReqMsg();

        ReqHead reqHead = new ReqHead();
        reqHead.setTxCode("1010");
        reqMsg.setReqHead(reqHead);

        ReqBody1010 reqBody1010 = new ReqBody1010();
        reqBody1010.setPrdtNm(prodId);

        String respStr = TcpUtil.tcpClient(reqMsg);
        RespMsg respMsg = XmlUtil.xmlToBean(respStr, RespMsg.class);
        String errcode = respMsg.getRespHead().getErrcode();
        if (!"0000".equals(errcode)) {
            return null;
        }
//        String acctNm = respMsg.getRespBody().getAcctNm();
//        String acctNo = respMsg.getRespBody().getAcctNo();

//        if (StringUtils.isEmpty(acctNm) || StringUtils.isEmpty(acctNo)) {
//            return null;
//        }

        return respMsg.getRespBody();
    }

    @Override
    public boolean auth(SendMsgCodeModel sendMsgCodeModel) {

        ReqMsg reqMsg = new ReqMsg();

        ReqHead reqHead = new ReqHead();
        reqHead.setTxCode("1009");
        reqMsg.setReqHead(reqHead);

        ReqBody1009 reqBody1009 = new ReqBody1009();
        reqBody1009.setAcctNo(sendMsgCodeModel.getCardId());
        reqBody1009.setAcctNm(sendMsgCodeModel.getRealName());
        reqBody1009.setIdno(sendMsgCodeModel.getIdcard());
        reqBody1009.setPhone(sendMsgCodeModel.getMobile());
        reqMsg.setReqBody1009(reqBody1009);

        String respStr = TcpUtil.tcpClient(reqMsg);

        RespMsg respMsg = XmlUtil.xmlToBean(respStr, RespMsg.class);
        String errcode = respMsg.getRespHead().getErrcode();
        if ("0000".equals(errcode)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean sendSms(String mobilePhone, String text) {
        ReqMsg reqMsg = new ReqMsg();

        ReqHead reqHead = new ReqHead();
        reqHead.setTxCode(HsBankConstants.SERVER_SENTSMS);
        reqMsg.setReqHead(reqHead);

        ReqBody1005 reqBody1005 = new ReqBody1005();
        reqBody1005.setMessage(text);
        reqBody1005.setPhone(mobilePhone);

        reqMsg.setReqBody1005(reqBody1005);
        String respStr = TcpUtil.tcpClient(reqMsg);

        RespMsg respMsg = XmlUtil.xmlToBean(respStr, RespMsg.class);
        System.out.println(respStr);

        String errcode = respMsg.getRespHead().getErrcode();
        if ("0000".equals(errcode)) {
            return true;
        }

        return false;
    }

    @Override
    public HsParam payProdForHS(Req2004 req2004) {
        HsParam hsParam = new HsParam();

        ReqMsg reqMsg = new ReqMsg();

        ReqHead reqHead = new ReqHead();
        reqHead.setTxCode("2001");
        reqMsg.setReqHead(reqHead);

        ReqBody1001 reqBody1001 = new ReqBody1001();
        reqBody1001.setAcctName(req2004.getAccountId());
        reqBody1001.setAcctNo(req2004.getAcctNo());
        reqBody1001.setAmount(req2004.getAmount());
        reqBody1001.setBrf(req2004.getBrf());
        reqBody1001.setIdNo(req2004.getIdNo());
        reqBody1001.setIdType(req2004.getIdType());
        reqBody1001.setPassType(req2004.getPassType());
        reqBody1001.setPassWd(req2004.getPassWd());
        reqMsg.setReqBody1001(reqBody1001);

        String respStr = TcpUtil.tcpClient(reqMsg);
        hsParam.setReqMsg(reqMsg);
        hsParam.setRespMsg(XmlUtil.xmlToBean(respStr, RespMsg.class));
        return hsParam;
    }


    public RespMsg returnforHs(Req2006 req2006, PayStatus payStatus) {
        ReqMsg reqMsg = new ReqMsg();

        ReqHead reqHead = new ReqHead();
        reqHead.setTxCode("2002");
        reqMsg.setReqHead(reqHead);

        ReqBody1002 reqBody1002 = new ReqBody1002();
        reqBody1002.setPassType(req2006.getPassType());
        reqBody1002.setOriChnlTraceNo(payStatus.getTraceNo());
        reqBody1002.setAcctNo(req2006.getAcctNo());
        reqBody1002.setAmount(req2006.getAmount());
        reqBody1002.setOriChnlDate(payStatus.getTxDate());
        reqBody1002.setPassWd(req2006.getPassWd());
        reqMsg.setReqBody1002(reqBody1002);

        String respStr = TcpUtil.tcpClient(reqMsg);
        RespMsg respMsg = XmlUtil.xmlToBean(respStr, RespMsg.class);

        return respMsg;
    }





    public RespMsg tuiKuan(PayStatus payStatus) {
        ReqMsg reqMsg = new ReqMsg();

        ReqHead reqHead = new ReqHead();
        reqHead.setTxCode("2002");
        reqMsg.setReqHead(reqHead);

        ReqBody1002 reqBody1002 = new ReqBody1002();
        reqBody1002.setPassType("N");
        reqBody1002.setOriChnlTraceNo(payStatus.getTraceNo());
        reqBody1002.setAcctNo(payStatus.getCardId());
        reqBody1002.setAmount(payStatus.getInvestmentAmount().doubleValue());
        reqBody1002.setOriChnlDate(payStatus.getTxDate());
        reqMsg.setReqBody1002(reqBody1002);

        String respStr = TcpUtil.tcpClient(reqMsg);
        RespMsg respMsg = XmlUtil.xmlToBean(respStr, RespMsg.class);

        String errcode = respMsg.getRespHead().getErrcode();
        if (errcode != null && "0000".equals(errcode)) {
            return respMsg;
        }
        return null;
    }

    @Override
    public String queryState(ReqBody1006 reqBody1006) {
        ReqMsg reqMsg = new ReqMsg();

        ReqHead reqHead = new ReqHead();
        reqHead.setTxCode("2006");
        reqMsg.setReqHead(reqHead);


        reqMsg.setReqBody1006(reqBody1006);

        String respStr = TcpUtil.tcpClient(reqMsg);
        RespMsg respMsg = XmlUtil.xmlToBean(respStr, RespMsg.class);

        String errcode = respMsg.getRespHead().getErrcode();
        if (errcode != null && "0000".equals(errcode)) {
            return respMsg.getRespBody().getState();
        }
        return null;
    }

}
