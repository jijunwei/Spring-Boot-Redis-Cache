/*
package com.springboot.service.send;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

@Service
public class ACKTCPClientService {
    @Autowired
    @Qualifier("ackSocket")
    Socket sck;

    @Value("${ackSocketServer.port}")
    */
/**
     端口号
     *//*

    public  int port;
    @Value("${ackSocketServer.IP}")
    */
/**
     * 服务器端ip地址
     *//*

    public  String ip;
    public String transportOut(String msg)throws  IOException{
        //1.建立TCP连接
       */
/* String ip="localhost";   //服务器端ip地址
        int port=5001;        //端口号
        Socket sck=new Socket(ip,port);*//*

        //2.传输内容
        String content= msg;

        byte[] bstream=content.getBytes("GBK");  //转化为字节流
        OutputStream os=sck.getOutputStream();   //输出流
        os.write(bstream);
        sck.shutdownOutput();

        //3.关闭连接
        sck.close();
        return "send completed!";
    }
}

*/
