package com.springboot.model.hsbank.req;

import javax.xml.bind.annotation.XmlElement;

public class ReqBody1004 {
    String intAcct;        //	定期账户 转存后的定期账户
    String intAcctSeq;        //	账户序号
    Double amount;        //	金额
    String PayeeAcct;        //	转入卡号
    String PayeeAcctName;
    String passType;        //	验密标志
    String passWd;        //	交易密码

    String payeeAcctName;

    @XmlElement(name = "intAcct")
    public String getIntAcct() {
        return intAcct;
    }

    public void setIntAcct(String intAcct) {
        this.intAcct = intAcct;
    }

    @XmlElement(name = "intAcctSeq")
    public String getIntAcctSeq() {
        return intAcctSeq;
    }

    public void setIntAcctSeq(String intAcctSeq) {
        this.intAcctSeq = intAcctSeq;
    }

    @XmlElement(name = "amount")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @XmlElement(name = "PayeeAcct")
    public String getPayeeAcct() {
        return PayeeAcct;
    }

    @XmlElement(name = "PayeeAcctName")
    public String getPayeeAcctName() {
        return PayeeAcctName;
    }

    public void setPayeeAcctName(String payeeAcctName) {
        PayeeAcctName = payeeAcctName;
    }

    public void setPayeeAcct(String payeeAcct) {
        PayeeAcct = payeeAcct;
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

    public ReqBody1004(String intAcct, String intAcctSeq, Double amount, String payeeAcct, String passType, String passWd) {
        this.intAcct = intAcct;
        this.intAcctSeq = intAcctSeq;
        this.amount = amount;
        PayeeAcct = payeeAcct;
        this.passType = passType;
        this.passWd = passWd;
    }

    public ReqBody1004() {
    }

    @Override
    public String toString() {
        return "ReqBody1004{" +
                "intAcct='" + intAcct + '\'' +
                ", intAcctSeq='" + intAcctSeq + '\'' +
                ", amount=" + amount +
                ", PayeeAcct='" + PayeeAcct + '\'' +
                ", passType='" + passType + '\'' +
                ", passWd='" + passWd + '\'' +
                '}';
    }
}
