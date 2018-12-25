package com.springboot.model.hsbank.req;

import javax.xml.bind.annotation.XmlElement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ReqHead {
    String txCode;
    String chnl;
    String txDate;
    String txTime;
    String traceNo;

    @XmlElement(name = "TxCode")
    public String getTxCode() {
        return txCode;
    }

    public void setTxCode(String txCode) {
        this.txCode = txCode;
    }

    @XmlElement(name = "Chnl")
    public String getChnl() {
        return chnl;
    }

    public void setChnl(String chnl) {
        this.chnl = chnl;
    }

    @XmlElement(name = "TxDate")
    public String getTxDate() {
        return txDate;
    }

    public void setTxDate(String txDate) {
        this.txDate = txDate;
    }

    @XmlElement(name = "TxTime")
    public String getTxTime() {
        return txTime;
    }

    public void setTxTime(String txTime) {
        this.txTime = txTime;
    }

    @XmlElement(name = "TraceNo")
    public String getTraceNo() {
        return traceNo;
    }

    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo;
    }

    @Override
    public String toString() {
        return "ReqHead{" +
                "txCode='" + txCode + '\'' +
                ", txDate='" + txDate + '\'' +
                ", txTime='" + txTime + '\'' +
                ", traceNo='" + traceNo + '\'' +
                '}';
    }

    public ReqHead(String txCode, String txDate, String txTime, String traceNo) {
        this.txCode = txCode;
        this.txDate = txDate;
        this.txTime = txTime;
        this.traceNo = traceNo;
    }

    public ReqHead() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String dateString = formatter.format(currentTime);
        String[] split = dateString.split("-");
        if (split != null && split.length == 2) {
            this.txDate = split[0];
            this.txTime = split[1];
        }
        this.traceNo = split[0] + UUID.randomUUID().toString().replace("-", "");
        this.chnl = "02";
    }

}
