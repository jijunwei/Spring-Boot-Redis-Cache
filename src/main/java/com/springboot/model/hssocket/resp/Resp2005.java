package com.springboot.model.hssocket.resp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "hsmsg")
public class Resp2005 {

    String serviceId;
    String code;
    String msg;
    Double totalInterest;
    Double   availableAmount;
    Integer listSize;
    List<OrderInfoForHs> orderInfos;

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

    @XmlElement(name = "totalInterest")
    public Double getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(Double totalInterest) {
        this.totalInterest = totalInterest;
    }

    @XmlElement(name = "availableAmount")
    public Double getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(Double availableAmount) {
        this.availableAmount = availableAmount;
    }

    @XmlElement(name = "listSize")
    public Integer getListSize() {
        return listSize;
    }

    public void setListSize(Integer listSize) {
        this.listSize = listSize;
    }

    @XmlElement(name = "orderInfo")
    public List<OrderInfoForHs> getOrderInfos() {
        return orderInfos;
    }

    public void setOrderInfos(List<OrderInfoForHs> orderInfos) {
        this.orderInfos = orderInfos;
    }

    public Resp2005() {
    }
}
