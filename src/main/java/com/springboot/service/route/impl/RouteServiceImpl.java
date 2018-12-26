package com.springboot.service.route.impl;

import com.springboot.bean.Student;
import com.springboot.config.redis.RedisConfig;
import com.springboot.model.route.RouteMsg;
import com.springboot.service.StudentService;
import com.springboot.service.pubsub.Publisher;
import com.springboot.service.route.RouteService;
import com.springboot.util.SpringUtil;
import com.springboot.util.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RouteServiceImpl implements RouteService {
    public static final String CHANNEL_NAME = "ProcessQueue";
    public static final String CHANNEL_NAME_Processing = "ProcessingQueue";
    public static final String CHANNEL_NAME_Processed = "processedQueue";
    @Autowired
    RedisConfig redisConfig;

    @Override
    public String process(String reqxml) {
        String result = "";
        RouteMsg routeMsg = (RouteMsg) XmlUtil.xmlToBean2(reqxml, RouteMsg.class);
        String channelCode = routeMsg.getChannelCode();
        if (channelCode.equals("HS116")) {
            Object student = routeMsg.getMsg();
            publish(XmlUtil.beanToXml(student,Student.class));

         /*   StudentService studentService = SpringUtil.getBean(StudentService.class);
            int i = studentService.add((Student) student);
            if (i == 1) {
                result = "add into db success!";
            } else {
                result = "fail!";
            }*/

        }

            return result;
        }


    public String publish(String message) {
        JedisPool JEDIS_POOL = redisConfig.redisPoolFactory();
        final Jedis publisherJedis = JEDIS_POOL.getResource();
        //主线程：发布消息到CHANNEL_NAME频道上
        new Publisher(publisherJedis, CHANNEL_NAME).startPublish(message);

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

}
