package com.springboot.model.hssocket.req;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "hsmsg")
public class Req2002 {

    String serviceId;
    String accountId;
    Integer page;
    Integer pageNum;

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

    @XmlElement(name = "page")
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @XmlElement(name = "pageNum")
    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Req2002() {
    }

    @Override
    public String toString() {
        return "Req2002{" +
                "serviceId='" + serviceId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", page=" + page +
                ", pageNum=" + pageNum +
                '}';
    }
}
