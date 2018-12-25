package com.springboot.model.hsbank.req;

import javax.xml.bind.annotation.XmlElement;

public class ReqBody1006 {
    String oriChnlDate;        //原渠道日期
    String oriChnlTrace;        //	原渠道流水

    @XmlElement(name = "oriChnlDate")
    public String getOriChnlDate() {
        return oriChnlDate;
    }

    public void setOriChnlDate(String oriChnlDate) {
        this.oriChnlDate = oriChnlDate;
    }

    @XmlElement(name = "oriChnlTrace")
    public String getOriChnlTrace() {
        return oriChnlTrace;
    }

    public void setOriChnlTrace(String oriChnlTrace) {
        this.oriChnlTrace = oriChnlTrace;
    }

    public ReqBody1006(String oriChnlDate, String oriChnlTrace) {
        this.oriChnlDate = oriChnlDate;
        this.oriChnlTrace = oriChnlTrace;
    }

    public ReqBody1006() {
    }

    @Override
    public String toString() {
        return "ReqBody1006{" +
                "oriChnlDate='" + oriChnlDate + '\'' +
                ", oriChnlTrace='" + oriChnlTrace + '\'' +
                '}';
    }
}
