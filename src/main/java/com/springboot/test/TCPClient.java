package com.springboot.test;

import com.springboot.model.bean.Student;
import com.springboot.model.route.RouteMsg;
import com.springboot.util.JaXmlBeanUtil;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {

    public static void main(String[] args) throws UnknownHostException, IOException {
        // TODO Auto-generated method stub
        //1.建立TCP连接
        String ip="localhost";   //服务器端ip地址
        int port=5000;        //端口号
        Socket sck=new Socket(ip,port);
        //2.传输内容

        Student student=new Student();
        student.setSno("005");
        student.setSex("male");
        student.setName("test");
        String studentXml=JaXmlBeanUtil.parseBeanToXml(Student.class,student);
        RouteMsg routeMsg=new RouteMsg();
        routeMsg.setChannelCode("HS116");
        routeMsg.setChannelSeq("T200000120130909000000001234");
        routeMsg.setChannelDate("20181226");
        routeMsg.setChannelTime("102109");
        routeMsg.setMsg(studentXml);

        //String content=XmlUtil.beanToXml(routeMsg,RouteMsg.class);
        String content= JaXmlBeanUtil.parseBeanToXml(RouteMsg.class,routeMsg);

        //third test method
        //String content="test message!";
        byte[] bstream=content.getBytes("GBK");  //转化为字节流
        OutputStream os=sck.getOutputStream();   //输出流
        os.write(bstream);
        sck.shutdownOutput();
        //获取输入流，并读取服务端处理信息
        InputStream is = sck.getInputStream();
        byte[] buff = new byte[1024];
        byte[] all = new byte[0];
        int len = 0;
        while((len = is.read(buff)) != -1){
            all = ArrayUtils.addAll(all,ArrayUtils.subarray(buff,0,len));
        }
        String resp = new String(all, "gbk");
        System.out.println("from socketServer after process:"+resp);
      //3.关闭连接
        sck.close();
    }


}

