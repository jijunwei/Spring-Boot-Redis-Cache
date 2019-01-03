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

@Service
public class HengShuiTCPClientService {
   /* @Autowired
    @Qualifier("destSocket")
    Socket dstsck;*/

    @Value("${destSocketServer.port}")
    /**
     端口号
     */
    public  int port;
    @Value("${destSocketServer.IP}")
    /**
     * 服务器端ip地址
     */
    public  String ip;
    public String transportOut(String msg) throws IOException{
        //1.建立TCP连接
       /* String ip="localhost";   //服务器端ip地址
        int port=5001;        //端口号
        Socket sck=new Socket(ip,port);*/
        //2.传输内容

        Socket dstsck = new Socket(ip, port);
        String content= msg;
        OutputStream os=null;
        InputStream is=null;
        String resp=null;
        try {
            byte[] bstream = content.getBytes("GBK");  //转化为字节流
             os= dstsck.getOutputStream();   //输出流
            os.write(bstream);
            dstsck.shutdownOutput();
            //获取输入流，并读取服务端处理信息
             is = dstsck.getInputStream();
            byte[] buff = new byte[1024];
            byte[] all = new byte[0];
            int len = 0;
            while ((len = is.read(buff)) != -1) {
                all = ArrayUtils.addAll(all, ArrayUtils.subarray(buff, 0, len));
            }
            resp = new String(all, "gbk");

            System.out.println("from socketServer[" + ip + ":" + port + "] :" + resp);


        }catch(Exception e){

        }finally {
            //关闭资源
            try {
                if(dstsck!=null){
                    dstsck.close();
                }
                if(os!=null) {
                    os.close();
                }
                if(is!=null){
                    is.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resp;
    }
}

