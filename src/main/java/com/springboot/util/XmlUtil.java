package com.springboot.util;


import com.springboot.model.hsbank.resp.RespBody;
import com.springboot.model.hsbank.resp.RespHead;
import com.springboot.model.hsbank.resp.RespMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlUtil {

    // 日志
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlUtil.class);

    /**
     * java对象转换为xml文件
     *
     * @param load java对象.Class
     * @return xml文件的String
     */
    public static String beanToXml(Object obj, Class<?> load) {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(load);
            Marshaller marshaller = context.createMarshaller();
//          marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            return writer.toString();
        } catch (JAXBException e) {
            LOGGER.error("beanToXml error : ", e);
        }
        return null;

    }

    /**
     * xml文件配置转换为对象
     *
     * @param load java对象.Class
     * @return java对象
     */
    public static RespMsg xmlToBean(String xmlStr, Class<?> load) {
        int indexOf = xmlStr.indexOf("<");
        xmlStr = xmlStr.substring(indexOf, xmlStr.length());
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(load);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader stringReader = new StringReader(xmlStr);
            InputSource inputSource = new InputSource(stringReader);
            Object object = unmarshaller.unmarshal(inputSource);
            return (RespMsg) object;
        } catch (JAXBException e) {
            LOGGER.error("xmlToBean error : ", e);
        }
        RespMsg respMsg = new RespMsg();
        respMsg.setRespBody(new RespBody());
        respMsg.setRespHead(new RespHead());
        return respMsg;

    }

    /**
     * xml文件配置转换为对象
     *
     * @param load java对象.Class
     * @return java对象
     */
    public static Object xmlToBean2(String xmlStr, Class<?> load) {
        int indexOf = xmlStr.indexOf("<");
        xmlStr = xmlStr.substring(indexOf, xmlStr.length());
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(load);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader stringReader = new StringReader(xmlStr);
            InputSource inputSource = new InputSource(stringReader);
            Object object = unmarshaller.unmarshal(inputSource);
            return object;
        } catch (JAXBException e) {
            LOGGER.error("xmlToBean error : ", e);
        }

        return null;

    }

}
