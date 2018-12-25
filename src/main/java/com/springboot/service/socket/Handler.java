package com.springboot.service.socket;


import com.springboot.service.hengshui.HSChannelService;
import com.springboot.util.SpringUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Handler implements Runnable {

    // 日志
    private static final Logger LOGGER = LoggerFactory.getLogger(Handler.class);

    private Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream is=null;
        OutputStream os=null;
        try {
            //获取输入流，并读取客户端信息
            is = socket.getInputStream();
            byte[] buff = new byte[1024];
            byte[] all = new byte[0];
            int len = 0;
            while((len = is.read(buff)) != -1){
                all = ArrayUtils.addAll(all,ArrayUtils.subarray(buff,0,len));
            }
            String reqxml = new String(all, "gbk");
            LOGGER.info("Handler request : " + reqxml);
            HSChannelService hsChannelService = SpringUtil.getBean(HSChannelService.class);
            String respxml =  hsChannelService.handle(reqxml);
            LOGGER.info("Handler response : " + respxml);
            socket.shutdownInput();//关闭输入流
            //获取输出流，响应客户端的请求
            os = socket.getOutputStream();
            os.write(respxml.getBytes("gbk"));
            os.flush();//调用flush()方法将缓冲输出
        } catch (IOException e) {
            LOGGER.error("socket handler error : ",e);
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
}