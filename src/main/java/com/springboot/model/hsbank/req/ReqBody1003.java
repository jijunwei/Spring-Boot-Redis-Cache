package com.springboot.model.hsbank.req;

import javax.xml.bind.annotation.XmlElement;

public class ReqBody1003 {

    String acctNo;//	卡号
    Double amount;//	金额
    String prdtNo;//	产品号
    String interestRate;//	利率
    String brf;//	摘要

    String oriChnlDate;        //原渠道日期
    String oriChnlTrace;        //	原渠道流水

    @XmlElement(name = "oriChnlDate")
    public String getOriChnlDate() {
        return oriChnlDate;
    }

    public void setOriChnlDate(String oriChnlDate) {
        this.oriChnlDate = oriChnlDate;
    }

    @XmlElement(name = "oriChnlTrace")
    public String getOriChnlTrace() {
        return oriChnlTrace;
    }

    public void setOriChnlTrace(String oriChnlTrace) {
        this.oriChnlTrace = oriChnlTrace;
    }

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

    @XmlElement(name = "prdtNo")
    public String getPrdtNo() {
        return prdtNo;
    }

    public void setPrdtNo(String prdtNo) {
        this.prdtNo = prdtNo;
    }

    @XmlElement(name = "interestRate")
    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    @XmlElement(name = "brf")
    public String getBrf() {
        return brf;
    }

    public void setBrf(String brf) {
        this.brf = brf;
    }

    public ReqBody1003() {
    }

    public ReqBody1003(String acctNo, Double amount, String prdtNo, String interestRate, String brf) {
        this.acctNo = acctNo;
        this.amount = amount;
        this.prdtNo = prdtNo;
        this.interestRate = interestRate;
        this.brf = brf;
    }

    @Override
    public String toString() {
        return "ReqBody1003{" +
                "acctNo='" + acctNo + '\'' +
                ", amount=" + amount +
                ", prdtNo='" + prdtNo + '\'' +
                ", interestRate='" + interestRate + '\'' +
                ", brf='" + brf + '\'' +
                '}';
    }
}
