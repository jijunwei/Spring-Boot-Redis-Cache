package com.springboot.model.hssocket.resp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "hsmsg")
public class Resp2002 {

    String serviceId;
    String code;
    String msg;
    Integer listSize;
    List<ProductItemForHs> productItemForHsList;

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

    @XmlElement(name = "listSize")
    public Integer getListSize() {
        return listSize;
    }

    public void setListSize(Integer listSize) {
        this.listSize = listSize;
    }

    @XmlElement(name = "productInfo")
    public List<ProductItemForHs> getProductItemForHsList() {
        return productItemForHsList;
    }

    public void setProductItemForHsList(List<ProductItemForHs> productItemForHsList) {
        this.productItemForHsList = productItemForHsList;
    }

    public Resp2002() {
    }
}
