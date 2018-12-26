package com.springboot.service.socket;

import com.springboot.bean.Student;
import com.springboot.model.hssocket.req.Req2001;
import com.springboot.model.route.RouteMsg;
import com.springboot.util.JaXmlBeanUtil;
import com.springboot.util.XmlUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClientDemo {

    public static void main(String[] args) throws UnknownHostException, IOException {
        // TODO Auto-generated method stub
        //1.建立TCP连接
        String ip="localhost";   //服务器端ip地址
        int port=5000;        //端口号
        Socket sck=new Socket(ip,port);
        //2.传输内容
        /*Req2001 req2001=new Req2001();
        req2001.setIdCard("2004101510117");
        req2001.setMobile("18519121911");
        req2001.setName("test");
        req2001.setServiceId("2001");
        String content= XmlUtil.beanToXml(req2001,Req2001.class);*/

        Student student=new Student();
        student.setSno("005");
        student.setSex("male");
        student.setName("test");

        RouteMsg routeMsg=new RouteMsg();
        routeMsg.setChannelCode("HS116");
        routeMsg.setChannelSeq("T200000120130909000000001234");
        routeMsg.setChannelDate("20181226");
        routeMsg.setChannelTime("102109");
        routeMsg.setMsg(student);

        //String content=XmlUtil.beanToXml(routeMsg,RouteMsg.class);
        String content= JaXmlBeanUtil.parseBeanToXml(RouteMsg.class,routeMsg);
        byte[] bstream=content.getBytes("GBK");  //转化为字节流
        OutputStream os=sck.getOutputStream();   //输出流
        os.write(bstream);
        //3.关闭连接
        sck.close();
    }

}

