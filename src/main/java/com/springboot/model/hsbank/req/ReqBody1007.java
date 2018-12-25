package com.springboot.model.hsbank.req;

import javax.xml.bind.annotation.XmlElement;

public class ReqBody1007 {

    String tradeType = "06";
    String tradeDetail = "060001";
    String phone;
    String message;

    @XmlElement(name = "TradeType")
    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    @XmlElement(name = "TradeDetail")
    public String getTradeDetail() {
        return tradeDetail;
    }

    public void setTradeDetail(String tradeDetail) {
        this.tradeDetail = tradeDetail;
    }

    @XmlElement(name = "Phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @XmlElement(name = "Message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ReqBody1007(String tradeType, String tradeDetail, String phone, String message) {
        this.tradeType = tradeType;
        this.tradeDetail = tradeDetail;
        this.phone = phone;
        this.message = message;
    }

    public ReqBody1007() {
    }

    @Override
    public String toString() {
        return "ReqBody1007{" +
                "tradeType='" + tradeType + '\'' +
                ", tradeDetail='" + tradeDetail + '\'' +
                ", phone='" + phone + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}

