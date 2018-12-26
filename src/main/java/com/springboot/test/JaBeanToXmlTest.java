package com.springboot.test;


import com.springboot.bean.JaBeanToXml;
import com.springboot.util.JaXmlBeanUtil;
import org.junit.Test;

public class JaBeanToXmlTest {
    /**
     * xml转javaBean
     */
    @Test
    public void parseXmlToJaBean() {
        //组装xml
        StringBuilder sb = new StringBuilder();
        sb.append("<root>");
        sb.append("<name>wangkecheng</name>");
        sb.append("<address>上海</address>");
        sb.append("<age>27</age>");
        sb.append("</root>");

        JaBeanToXml jaBeanToXml = (JaBeanToXml) JaXmlBeanUtil.parseXmlToBean(JaBeanToXml.class,sb.toString());
        System.out.println(jaBeanToXml.toString());
    }

    /**
     * bean转xml
     */
    @Test
    public void parseJaBeanToXml(){

        JaBeanToXml jaBeanToXml = new JaBeanToXml();
        jaBeanToXml.setName("wangkecheng");
        jaBeanToXml.setAddress("上海");
        jaBeanToXml.setAge(28);

        String xml = JaXmlBeanUtil.parseBeanToXml(JaBeanToXml.class,jaBeanToXml);
        System.out.println(xml);
    }
}
