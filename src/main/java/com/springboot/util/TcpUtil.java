package com.springboot.util;


import com.springboot.model.hsbank.req.ReqMsg;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TcpUtil {

    private static String host = "127.0.0.1";
//    private static String host = "10.1.150.167";
    private static int port = 5001;
    private static String stringFormat = "%08d";

    // 日志
    private static final Logger LOGGER = LoggerFactory.getLogger(TcpUtil.class);

    public static String tcpClient(ReqMsg reqMsg) {

        Socket socket = null;
        OutputStream os = null;
        BufferedReader br = null;
        InputStream is = null;

        try {
            //1.创建客户端Socket，指定服务器地址和端口
            socket = new Socket(host, port);
            //2.获取输出流，向服务器端发送信息
            os = socket.getOutputStream();//字节输出流

            String beanToXml = XmlUtil.beanToXml(reqMsg, ReqMsg.class);

            String reqXml = String.format(stringFormat, beanToXml.getBytes("GBK").length) + beanToXml;
            LOGGER.info("hengshui tcp reqXml:{" + reqXml + "}");

            os.write(reqXml.getBytes("GBK"));
            socket.shutdownOutput();//关闭输出流
            //3.获取输入流，并读取服务器端的响应信息
            is = socket.getInputStream();
            StringBuffer reqSb = new StringBuffer();
            byte[] buff = new byte[1024];
            byte[] all = new byte[0];
            int len = 0;
            while ((len = is.read(buff)) != -1) {
                all = ArrayUtils.addAll(all, ArrayUtils.subarray(buff, 0, len));
            }
            String respXml = new String(all, "GBK");
            LOGGER.info("hengshui tcp respXml:{" + respXml + "}");
            return respXml;
        } catch (Exception e) {
            LOGGER.error("hsbnak api req error : ", e);
        } finally {
            //4.关闭资源
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
            }
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (Exception e) {
            }
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

}
