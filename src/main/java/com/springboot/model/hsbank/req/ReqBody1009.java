package com.springboot.model.hsbank.req;

import javax.xml.bind.annotation.XmlElement;

public class ReqBody1009 {

    String acctNo;
    String acctNm;
    String phone;
    String idno;

    @XmlElement(name = "AcctNo")
    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    @XmlElement(name = "AcctNm")
    public String getAcctNm() {
        return acctNm;
    }

    public void setAcctNm(String acctNm) {
        this.acctNm = acctNm;
    }

    @XmlElement(name = "Phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @XmlElement(name = "Idno")
    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public ReqBody1009(String acctNo, String acctNm, String phone, String idno) {
        this.acctNo = acctNo;
        this.acctNm = acctNm;
        this.phone = phone;
        this.idno = idno;
    }

    public ReqBody1009() {
    }

    @Override
    public String toString() {
        return "ReqBody1008{" +
                "acctNo='" + acctNo + '\'' +
                ", acctNm='" + acctNm + '\'' +
                ", phone='" + phone + '\'' +
                ", idno='" + idno + '\'' +
                '}';
    }
}
