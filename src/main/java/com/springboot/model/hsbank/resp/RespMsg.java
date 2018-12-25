package com.springboot.model.hsbank.resp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Msg")
public class RespMsg {

    RespHead respHead;

    RespBody respBody;

    @XmlElement(name = "Head")
    public RespHead getRespHead() {
        return respHead;
    }

    public void setRespHead(RespHead respHead) {
        this.respHead = respHead;
    }


    @XmlElement(name = "Body")
    public RespBody getRespBody() {
        return respBody;
    }

    public void setRespBody(RespBody respBody) {
        this.respBody = respBody;
    }

    @Override
    public String toString() {
        return "RespMsg{" +
                "respHead=" + respHead +
                ", respBody=" + respBody +
                '}';
    }

    public RespMsg() {
    }
}
