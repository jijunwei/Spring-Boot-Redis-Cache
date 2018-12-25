package com.springboot.model.hssocket.resp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "hsmsg")
public class Resp2003 {

    String serviceId;
    String code;
    String msg;
    Long productId;
    String productName;
    String productDuration;
    BigDecimal initialDeposit;
    Double interestRate;
    Double miniAmount;
    Integer raiseTimeLimit;
    String feature;
    String transactionRule;
    Double currentAmount;
    Long orderId;
    Integer joined;
    String startTime;

    @XmlElement(name = "miniAmount")
    public Double getMiniAmount() {
        return miniAmount;
    }

    public void setMiniAmount(Double miniAmount) {
        this.miniAmount = miniAmount;
    }

    @XmlElement(name = "serviceId")
    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @XmlElement(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlElement(name = "msg")
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @XmlElement(name = "productId")
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @XmlElement(name = "productName")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @XmlElement(name = "productDuration")
    public String getProductDuration() {
        return productDuration;
    }

    public void setProductDuration(String productDuration) {
        this.productDuration = productDuration;
    }

    @XmlElement(name = "initialDeposit")
    public BigDecimal getInitialDeposit() {
        return initialDeposit;
    }

    public void setInitialDeposit(BigDecimal initialDeposit) {
        this.initialDeposit = initialDeposit;
    }

    @XmlElement(name = "interestRate")
    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    @XmlElement(name = "raiseTimeLimit")
    public Integer getRaiseTimeLimit() {
        return raiseTimeLimit;
    }

    public void setRaiseTimeLimit(Integer raiseTimeLimit) {
        this.raiseTimeLimit = raiseTimeLimit;
    }

    @XmlElement(name = "feature")
    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    @XmlElement(name = "transactionRule")
    public String getTransactionRule() {
        return transactionRule;
    }

    public void setTransactionRule(String transactionRule) {
        this.transactionRule = transactionRule;
    }

    @XmlElement(name = "currentAmount")
    public Double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Double currentAmount) {
        this.currentAmount = currentAmount;
    }

    @XmlElement(name = "orderId")
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @XmlElement(name = "joined")
    public Integer getJoined() {
        return joined;
    }

    public void setJoined(Integer joined) {
        this.joined = joined;
    }

    @XmlElement(name = "startTime")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Resp2003() {
    }
}
