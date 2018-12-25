package com.springboot.model.hssocket.req;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "hsmsg")
public class Req2004 {

    String serviceId;
    String accountId;
    String managerId;
    String acctNo;
    String passType;
    String passWd;
    String idType;
    String idNo;
    String brf;
    Long orderId;
    Double amount;

    @XmlElement(name = "managerId")
    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    @XmlElement(name = "serviceId")
    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @XmlElement(name = "accountId")
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @XmlElement(name = "acctNo")
    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
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

    @XmlElement(name = "orderId")
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @XmlElement(name = "amount")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Req2004() {
    }

    @Override
    public String toString() {
        return "Req2004{" +
                "serviceId='" + serviceId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", acctNo='" + acctNo + '\'' +
                ", passType='" + passType + '\'' +
                ", passWd='" + passWd + '\'' +
                ", idType='" + idType + '\'' +
                ", idNo='" + idNo + '\'' +
                ", brf='" + brf + '\'' +
                ", orderId=" + orderId +
                ", amount=" + amount +
                '}';
    }

}

