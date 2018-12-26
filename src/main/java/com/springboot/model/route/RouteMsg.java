package com.springboot.model.route;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;


@XmlRootElement(name = "routemsg")
public class RouteMsg implements Serializable {
    @XmlElement(name = "channelDate")
    //渠道交易日期,格式为 YYYYMMDD
    String channelDate;
    //格式为 HHMMSSNNN
    @XmlElement(name = "channelTime")
    String channelTime;
    //渠道流水号,表示渠道的唯一流水,前缀（T）+源系统标识号（7 位）+源系统交易日期（8 位：YYYYMMDD）+交易流水序号（12 位）
    @XmlElement(name = "channelSeq")
    String channelSeq;
    //渠道系统标识:合作方系统标识
    @XmlElement(name = "channelCode")
    String channelCode;
    @XmlElement(name = "msg")
    Object Msg;

    @XmlTransient
    public Object getMsg() {
        return Msg;
    }
    @XmlTransient
    public String getChannelCode() {
        return channelCode;
    }
    @XmlTransient
    public String getChannelDate(){
        return channelDate;
    }
    @XmlTransient
    public String getChannelTime() {
        return channelTime;
    }
    @XmlTransient
    public String getChannelSeq() {
        return channelSeq;
    }
    public void setChannelDate(String channelDate){
        this.channelDate=channelDate;
    }
    public void setChannelTime(String channelTime){
        this.channelTime=channelTime;
    }
    public void setChannelSeq(String channelSeq){
        this.channelSeq=channelSeq;
    }

    public void setMsg(Object msg) {
        Msg = msg;
    }
}
