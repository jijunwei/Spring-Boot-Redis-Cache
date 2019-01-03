/*
package com.springboot.config.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.Socket;


@Configuration
public class AckSocketConfig {
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
    Logger logger= LoggerFactory.getLogger(AckSocketConfig.class);
    @Bean(name="ackSocket")
    public Socket ackSocket(){
        try {
            Socket sck = new Socket(ip, port);
            return sck;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
       return null;

    }

}
*/
