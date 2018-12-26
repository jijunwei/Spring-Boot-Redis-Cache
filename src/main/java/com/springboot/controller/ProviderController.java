package com.springboot.controller;


import com.springboot.config.redis.RedisConfig;
import com.springboot.service.pubsub.MyListener;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CountDownLatch;


@RestController
@RequestMapping("/provider")
public class ProviderController {

   private final static Logger LOGGER = Logger.getLogger(ProviderController.class);


    @Autowired
    RedisConfig redisConfig;
    @Autowired
    MyListener listener;
    @Autowired
    CountDownLatch latch;
    @Autowired
    StringRedisTemplate template;





    @RequestMapping("/testProvider")
    @ResponseBody
    public String testProvider() {
        System.out.println("我要发送消息咯...");
        template.convertAndSend("msg", "欢迎使用redis的消息队列!");
        try {
            //发送消息连接等待中
            System.out.println("消息正在发送...");
            latch.await();
        } catch (InterruptedException e) {
            System.out.println("消息发送失败...");
        }
        return null;
    }



}

