package com.springboot.model.hssocket.resp;

import javax.xml.bind.annotation.XmlElement;

public class OrderInfoForHs {

    Long orderId;
    String productName;
    Integer orderStatus;
    String productDuration;
    Double interestRate;
    Double investmentAmount;
    Double anticipatedInterest;
    String cardId;
    Integer payStatus;
    String createTime;
    String updateTime;
    String startTime;
    String expireTime;
    private Long productDurationValue;//产品期限
    private Integer productDurationUnits;//产品期限的单位

    @XmlElement(name = "orderId")
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @XmlElement(name = "productName")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @XmlElement(name = "orderStatus")
    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    @XmlElement(name = "productDuration")
    public String getProductDuration() {
        return productDuration;
    }

    public void setProductDuration(String productDuration) {
        this.productDuration = productDuration;
    }

    @XmlElement(name = "interestRate")
    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    @XmlElement(name = "investmentAmount")
    public Double getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(Double investmentAmount) {
        this.investmentAmount = investmentAmount;
    }

    @XmlElement(name = "anticipatedInterest")
    public Double getAnticipatedInterest() {
        return anticipatedInterest;
    }

    public void setAnticipatedInterest(Double anticipatedInterest) {
        this.anticipatedInterest = anticipatedInterest;
    }

    @XmlElement(name = "cardId")
    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    @XmlElement(name = "payStatus")
    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    @XmlElement(name = "createTime")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @XmlElement(name = "updateTime")
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @XmlElement(name = "startTime")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @XmlElement(name = "expireTime")
    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public Long getProductDurationValue() {
        return productDurationValue;
    }

    public void setProductDurationValue(Long productDurationValue) {
        this.productDurationValue = productDurationValue;
    }

    public Integer getProductDurationUnits() {
        return productDurationUnits;
    }

    public void setProductDurationUnits(Integer productDurationUnits) {
        this.productDurationUnits = productDurationUnits;
    }
}
