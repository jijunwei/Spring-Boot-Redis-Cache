package com.springboot.model.hsbank.req;

import javax.xml.bind.annotation.XmlElement;

public class ReqBody1001 {

    String acctNo;//卡号
    String acctName;//账户名称
    Double amount;//金额
    String passType;//验密标志  Y 验密 N 不验密
    String passWd;//交易密码
    String idType; //证件类型 1-身份证
    String idNo;//证件号码
    String brf;//摘要

    @XmlElement(name = "acctNo")
    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    @XmlElement(name = "acctName")
    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
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

    @XmlElement(name = "idType")
    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    @XmlElement(name = "idNo")
    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    @XmlElement(name = "brf")
    public String getBrf() {
        return brf;
    }

    public void setBrf(String brf) {
        this.brf = brf;
    }

    public ReqBody1001() {
    }

    public ReqBody1001(String acctNo, String acctName, Double amount, String passType, String passWd, String idType, String idNo, String brf) {
        this.acctNo = acctNo;
        this.acctName = acctName;
        this.amount = amount;
        this.passType = passType;
        this.passWd = passWd;
        this.idType = idType;
        this.idNo = idNo;
        this.brf = brf;
    }

    @Override
    public String toString() {
        return "ReqBody1001{" +
                "acctNo='" + acctNo + '\'' +
                ", acctName='" + acctName + '\'' +
                ", amount=" + amount +
                ", passType='" + passType + '\'' +
                ", passWd='" + passWd + '\'' +
                ", idType='" + idType + '\'' +
                ", idNo='" + idNo + '\'' +
                ", brf='" + brf + '\'' +
                '}';
    }
}
