package com.springboot.service.socket;


import ch.qos.logback.core.joran.spi.XMLUtil;
import com.springboot.bean.Student;
import com.springboot.model.route.RouteMsg;
import com.springboot.service.StudentService;
import com.springboot.service.hengshui.HSChannelService;
import com.springboot.service.route.RouteService;
import com.springboot.util.SpringUtil;
import com.springboot.util.XmlUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * nio socket服务端
 */
public class SocketServer {

    // 日志
    private static final Logger LOGGER = LoggerFactory.getLogger(SocketServer.class);

    /**
     * 启动socket服务，开启监听
     * @param port
     * @throws IOException
     */
    public void startSocketServer(int port){
        try {
            //1.创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
            ServerSocket serverSocket=new ServerSocket(port);
            Socket socket=null;
            //记录客户端的数量
            int count=0;
            System.out.println("***服务器即将启动，等待客户端的连接***");
            //循环监听等待客户端的连接
            while(true){
                //调用accept()方法开始监听，等待客户端的连接
                socket=serverSocket.accept();
                InputStream is=null;
                OutputStream os=null;
                try {
                    StringBuffer reqSb = new StringBuffer();
                    //获取输入流，并读取客户端信息
                    is = socket.getInputStream();
                    byte[] buff = new byte[1024];
                    byte[] all = new byte[0];
                    int len = 0;
                    while((len = is.read(buff)) != -1){
                        all = ArrayUtils.addAll(all,ArrayUtils.subarray(buff,0,len));
                    }
                    String reqxml = new String(all, "gbk");
                    RouteService routeService=SpringUtil.getBean(RouteService.class);
                    String result=routeService.process(reqxml);

                    /*HSChannelService hsChannelService = SpringUtil.getBean(HSChannelService.class);

                    String respxml =  hsChannelService.handle(reqxml);*/

                    socket.shutdownInput();//关闭输入流
                    //获取输出流，响应客户端的请求
                    os = socket.getOutputStream();
                    //os.write(respxml.getBytes("gbk"));

                    os.write(result.getBytes("gbk"));
                    os.flush();//调用flush()方法将缓冲输出
                } catch (IOException e) {
                    LOGGER.error("",e);
                }finally{
                    //关闭资源
                    try {
                        if(os!=null) {
                            os.close();
                        }
                        if(is!=null) {
                            is.close();
                        }
                        if(socket!=null) {
                            socket.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

