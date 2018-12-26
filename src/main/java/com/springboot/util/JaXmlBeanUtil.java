package com.springboot.util;

import com.springboot.bean.XmlElementAnno;
import org.apache.commons.beanutils.BeanUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * xml与bean互转工具类
 */
public class JaXmlBeanUtil {

    /**
     * 将javaBean转换为xml对象
     * @param clazz
     * @param bean
     * @return
     */
    public static String parseBeanToXml(Class clazz,Object bean){
        StringWriter sw = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            sw = new StringWriter();
            //该值默认为false,true则不会创建即头信息,即<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            jaxbMarshaller.marshal(bean, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    /**
     *将xml对象转换为javaBean
     * @param clazz
     * @param xml
     * @return
     */
    public static Object parseXmlToBean(Class clazz,String xml){
        if(xml!=null&&xml!=""){
            Field[] fields = clazz.getDeclaredFields();
            List<Field> fieldList = new ArrayList<Field>();
            for (Field fie : fields) {
                if (fie.isAnnotationPresent(XmlElementAnno.class)) {
                    fieldList.add(fie);
                }
            }
            try {
                StringReader read = new StringReader(xml);
                InputSource source = new InputSource(read);
                //创建一个新的SAXBuilder
                SAXBuilder sb = new SAXBuilder();
                Document doc = sb.build(source);
                //取的根元素
                Element root = doc.getRootElement();
                Object object = clazz.newInstance();
                if(!fieldList.isEmpty()){
                    for (Field field : fieldList) {
                        Element child = root.getChild(field.getName());
                        if(child!=null){
                            BeanUtils.setProperty(object, field.getName(), child.getValue());
                        }
                    }
                }
                return object;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
