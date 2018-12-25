package com.springboot.model.hsbank.req;

import javax.xml.bind.annotation.XmlElement;

public class ReqBody1002 {

    String acctNo;	//卡号
    Double amount;	//金额
    String passType;	//验密标志	Y 验密  N 不验密
    String passWd;	//交易密码密文
    String  oriChnlDate;	//原购买渠道日期
    String oriChnlTraceNo;	//原购买渠道流水

    @XmlElement(name = "acctNo")
    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    @XmlElement(name = "amount")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @XmlElement(name = "passType")
    public String getPassType() {
        return passType;
    }

    public void setPassType(String passType) {
        this.passType = passType;
    }

    @XmlElement(name = "passWd")
    public String getPassWd() {
        return passWd;
    }

    public void setPassWd(String passWd) {
        this.passWd = passWd;
    }

    @XmlElement(name = "oriChnlDate")
    public String getOriChnlDate() {
        return oriChnlDate;
    }

    public void setOriChnlDate(String oriChnlDate) {
        this.oriChnlDate = oriChnlDate;
    }

    @XmlElement(name = "oriChnlTraceNo")
    public String getOriChnlTraceNo() {
        return oriChnlTraceNo;
    }

    public void setOriChnlTraceNo(String oriChnlTraceNo) {
        this.oriChnlTraceNo = oriChnlTraceNo;
    }

    public ReqBody1002() {
    }

    public ReqBody1002(String acctNo, Double amount, String passType, String passWd, String oriChnlDate, String oriChnlTraceNo) {
        this.acctNo = acctNo;
        this.amount = amount;
        this.passType = passType;
        this.passWd = passWd;
        this.oriChnlDate = oriChnlDate;
        this.oriChnlTraceNo = oriChnlTraceNo;
    }

    @Override
    public String toString() {
        return "ReqBody1002{" +
                "acctNo='" + acctNo + '\'' +
                ", amount=" + amount +
                ", passType='" + passType + '\'' +
                ", passWd='" + passWd + '\'' +
                ", oriChnlDate='" + oriChnlDate + '\'' +
                ", oriChnlTraceNo='" + oriChnlTraceNo + '\'' +
                '}';
    }
}
