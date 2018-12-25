package com.springboot.model.hsbank.req;

import javax.xml.bind.annotation.XmlElement;

public class ReqBody1010 {

    String endDate = "99999999";
    String prdtNm;

    @XmlElement(name = "EndDate")
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @XmlElement(name = "PrdtNm")
    public String getPrdtNm() {
        return prdtNm;
    }

    public void setPrdtNm(String prdtNm) {
        this.prdtNm = prdtNm;
    }

    public ReqBody1010(String endDate, String prdtNm) {
        this.endDate = endDate;
        this.prdtNm = prdtNm;
    }

    public ReqBody1010() {
    }

    @Override
    public String toString() {
        return "ReqBody1010{" +
                "endDate='" + endDate + '\'' +
                ", prdtNm='" + prdtNm + '\'' +
                '}';
    }

}
