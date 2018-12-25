package com.springboot.model.hssocket.resp;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

public class ProductItemForHs {

    Long productId;
    String productName;
    String productDuration;
    BigDecimal initialDeposit;
    Double interestRate;
    Integer raiseTimeLimit;

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

    public ProductItemForHs() {
    }

    @Override
    public String toString() {
        return "ProductItemForHs{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDuration='" + productDuration + '\'' +
                ", initialDeposit=" + initialDeposit +
                ", interestRate=" + interestRate +
                ", raiseTimeLimit=" + raiseTimeLimit +
                '}';
    }
}
