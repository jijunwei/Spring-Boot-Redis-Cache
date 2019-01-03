package com.springboot.model.route;

import com.springboot.model.bean.XmlElementAnno;
import lombok.Data;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@Data
@XmlRootElement(name = "sourcemsg")
public class SourceMsg implements Serializable {
    @XmlElement(name = "channelDate")
    @Column(name = "channelDate")
    @XmlElementAnno
    //渠道交易日期,格式为 YYYYMMDD
    String channelDate;
    //渠道交易时间，格式为 HHMMSSNNN
    @Column(name = "channelTime")
    @XmlElement(name = "channelTime")
    @XmlElementAnno
    String channelTime;
    //渠道流水号,表示渠道的唯一流水,前缀（T）+源系统标识号（7 位）+源系统交易日期（8 位：YYYYMMDD）+交易流水序号（12 位）
    @XmlElement(name = "channelSeq")
    @Column(name = "channelSeq")
    @XmlElementAnno
    String channelSeq;
    //渠道系统标识:合作方系统标识
    @XmlElement(name = "channelCode")
    @Column(name = "channelCode")
    @XmlElementAnno
    String channelCode;
    @XmlElement(name = "msg")
    @Column(name = "msg")
    @XmlElementAnno
    String msg;

    @XmlTransient
    public String getMsg() {
        return msg;
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

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
