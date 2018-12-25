package com.springboot.model.hsbank.resp;

import javax.xml.bind.annotation.XmlElement;

public class RespHead {

    String errcode;
    String errmsg;
    String bpDate;
    String bpTime;
    String bpTrace;

    @XmlElement(name = "Errcode")
    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    @XmlElement(name = "Errmsg")
    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @XmlElement(name = "BpDate")
    public String getBpDate() {
        return bpDate;
    }

    public void setBpDate(String bpDate) {
        this.bpDate = bpDate;
    }

    @XmlElement(name = "BpTime")
    public String getBpTime() {
        return bpTime;
    }

    public void setBpTime(String bpTime) {
        this.bpTime = bpTime;
    }

    @XmlElement(name = "BpTrace")
    public String getBpTrace() {
        return bpTrace;
    }

    public void setBpTrace(String bpTrace) {
        this.bpTrace = bpTrace;
    }

    public RespHead() {
    }

    public RespHead(String errcode, String errmsg, String bpDate, String bpTime, String bpTrace) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.bpDate = bpDate;
        this.bpTime = bpTime;
        this.bpTrace = bpTrace;
    }

    @Override
    public String toString() {
        return "RespHead{" +
                "errcode='" + errcode + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", bpDate='" + bpDate + '\'' +
                ", bpTime='" + bpTime + '\'' +
                ", bpTrace='" + bpTrace + '\'' +
                '}';
    }
}
