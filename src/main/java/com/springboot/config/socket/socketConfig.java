/*
package com.springboot.config.socket;

import groovy.util.logging.Log4j;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.Socket;


@Configuration
public class socketConfig {
    @Value("${socketServer.port}")
    */
/**
    端口号
     *//*

    public  int port;
    @Value("${socketServer.IP}")
    */
/**
     * 服务器端ip地址
     *//*

    public  String ip;
    Logger logger= LoggerFactory.getLogger(socketConfig.class);
    @Bean(name="localSocket")
    public Socket newSocket(){
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
