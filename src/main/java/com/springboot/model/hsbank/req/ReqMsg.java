package com.springboot.model.hsbank.req;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Msg")
public class ReqMsg {

    ReqHead reqHead;

    ReqBody1001 reqBody1001;

    ReqBody1002 reqBody1002;

    ReqBody1003 reqBody1003;

    ReqBody1004 reqBody1004;

    ReqBody1005 reqBody1005;

    ReqBody1006 reqBody1006;

    ReqBody1007 reqBody1007;

    ReqBody1009 reqBody1009;

    ReqBody1010 reqBody1010;

    @XmlElement(name = "Head")
    public ReqHead getReqHead() {
        return reqHead;
    }

    public void setReqHead(ReqHead head) {
        reqHead = head;
    }

    @XmlElement(name = "Body")
    public ReqBody1001 getReqBody1001() {
        return reqBody1001;
    }

    public void setReqBody1001(ReqBody1001 reqBody1001) {
        this.reqBody1001 = reqBody1001;
    }

    @XmlElement(name = "Body")
    public ReqBody1002 getReqBody1002() {
        return reqBody1002;
    }

    public void setReqBody1002(ReqBody1002 reqBody1002) {
        this.reqBody1002 = reqBody1002;
    }

    @XmlElement(name = "Body")
    public ReqBody1003 getReqBody1003() {
        return reqBody1003;
    }

    public void setReqBody1003(ReqBody1003 reqBody1003) {
        this.reqBody1003 = reqBody1003;
    }

    @XmlElement(name = "Body")
    public ReqBody1004 getReqBody1004() {
        return reqBody1004;
    }

    public void setReqBody1004(ReqBody1004 reqBody1004) {
        this.reqBody1004 = reqBody1004;
    }

    @XmlElement(name = "Body")
    public ReqBody1005 getReqBody1005() {
        return reqBody1005;
    }

    public void setReqBody1005(ReqBody1005 reqBody1005) {
        this.reqBody1005 = reqBody1005;
    }

    @XmlElement(name = "Body")
    public ReqBody1006 getReqBody1006() {
        return reqBody1006;
    }

    public void setReqBody1006(ReqBody1006 reqBody1006) {
        this.reqBody1006 = reqBody1006;
    }

    @XmlElement(name = "Body")
    public ReqBody1007 getReqBody1007() {
        return reqBody1007;
    }

    public void setReqBody1007(ReqBody1007 reqBody1007) {
        this.reqBody1007 = reqBody1007;
    }

    @XmlElement(name = "Body")
    public ReqBody1009 getReqBody1009() {
        return reqBody1009;
    }

    public void setReqBody1009(ReqBody1009 reqBody1009) {
        this.reqBody1009 = reqBody1009;
    }

    @XmlElement(name = "Body")
    public ReqBody1010 getReqBody1010() {
        return reqBody1010;
    }

    public void setReqBody1010(ReqBody1010 reqBody1010) {
        this.reqBody1010 = reqBody1010;
    }

    @Override
    public String toString() {
        return "ReqMsg{" +
                "reqHead=" + reqHead +
                '}';
    }
}
