package com.springboot.model.hsbank.resp;

import javax.xml.bind.annotation.XmlElement;

public class RespBody {

    String intAcct;
    String intAcctSeq;
    Double bal;         //活期余额
    Double regPrin;     //定期本金
    Double interest;    //实付利息
    String state;       //交易状态  0 成功1 失败2 处理中

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

    @XmlElement(name = "bal")
    public Double getBal() {
        return bal;
    }

    public void setBal(Double bal) {
        this.bal = bal;
    }

    @XmlElement(name = "regPrin")
    public Double getRegPrin() {
        return regPrin;
    }

    public void setRegPrin(Double regPrin) {
        this.regPrin = regPrin;
    }

    @XmlElement(name = "interest")
    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    @XmlElement(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public RespBody() {
    }
}
