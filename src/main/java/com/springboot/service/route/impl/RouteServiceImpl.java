package com.springboot.service.route.impl;

import com.springboot.config.redis.RedisConfig;
import com.springboot.model.route.RouteMsg;
import com.springboot.model.route.RouteRule;
import com.springboot.model.route.SourceMsg;
import com.springboot.service.pubsub.Publisher;
import com.springboot.service.route.RouteService;
import com.springboot.service.send.HengShuiTCPClientService;
import com.springboot.util.JaXmlBeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RouteServiceImpl implements RouteService {
    public static final String CHANNEL_NAME = "thirdPlatformProcessQueue";
    public static final String CHANNEL_NAME_Processing = "thirdPlatformProcessingQueue";
    public static final String CHANNEL_NAME_Processed = "thirdPlatformProcessedQueue";
    @Autowired
    RedisConfig redisConfig;
    @Autowired
    HengShuiTCPClientService hengShuiTcpClientService;
    @Override
    public String process(String reqxml) {
        Logger logger= LoggerFactory.getLogger(this.getClass());
        String result = "";
        SourceMsg sourceMsg = (SourceMsg) JaXmlBeanUtil.parseXmlToBean(SourceMsg.class,reqxml);

        String channelCode = sourceMsg.getChannelCode();
        if (channelCode.equals("XWbank")) {
            String studentxml = sourceMsg.getMsg();
            //放入本机消息队列

            //publish(studentxml,CHANNEL_NAME);
            //转发
            String destBank=getOneRouteRulePath(channelCode,"hand");
            RouteMsg routeMsg=new RouteMsg();
            routeMsg.setChannelTime(sourceMsg.getChannelTime());
            routeMsg.setChannelDate(sourceMsg.getChannelDate());
            routeMsg.setChannelSeq(sourceMsg.getChannelSeq());
            routeMsg.setChannelCode(channelCode);
            routeMsg.setDestBankCode(destBank);;
            routeMsg.setPlatformCode("xujin001");
            routeMsg.setMsg(studentxml);
            String nextReqxml=JaXmlBeanUtil.parseBeanToXml(RouteMsg.class,routeMsg);

            if(destBank.equals("HengShuiBank")) {
                try {
                    result=hengShuiTcpClientService.transportOut(nextReqxml);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }


        }

        return result;
    }


    public String publish(String message,String channel_name) {
        JedisPool JEDIS_POOL = redisConfig.redisPoolFactory();
        final Jedis publisherJedis = JEDIS_POOL.getResource();
        //主线程：发布消息到CHANNEL_NAME频道上
        new Publisher(publisherJedis, channel_name).startPublish(message);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread();
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("发布：");
                //发布消息
                publisherJedis.publish(CHANNEL_NAME, message);
            }
        }).start();
        publisherJedis.close();
        return "publish ok";
    }
    @Override
    public String getOneRouteRulePath(String sourceChannelCode,String hand){
        RouteRule routeRule=new RouteRule();
        routeRule.initRouteByHand();
       String destBank= routeRule.getRoute(sourceChannelCode,hand);
        return destBank;
    }

}
