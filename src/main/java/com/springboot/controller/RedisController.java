package com.springboot.controller;


import com.springboot.bean.Student;
import com.springboot.config.RedisConfig;
import com.springboot.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.CountDownLatch;


@RestController
@RequestMapping("/redis/api")
public class RedisController {

    @Autowired
    private StudentService studentService;
    public static final String CHANNEL_NAME = "ProcessQueue";
    public static final String CHANNEL_NAME_Processing = "ProcessingQueue";
    public static final String CHANNEL_NAME_Processed = "processedQueue";


    private final static Logger LOGGER = Logger.getLogger(RedisController.class);


    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    RedisConfig redisConfig;
    @Autowired
    MyListener listener;
    @Autowired
    CountDownLatch latch;
    @Autowired
    StringRedisTemplate template;

    @RequestMapping( value = "/publish", method = RequestMethod.POST)
    @ResponseBody
    public String publishOps(@RequestBody String message) {
       /* AuthUser authUser = (AuthUser) redisTemplate.opsForValue().get(Constants.PRE_TOKEN + token);
        if (Objects.isNull(authUser)) {
            return null;
        }*/
        JedisPool JEDIS_POOL=redisConfig.redisPoolFactory();
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
                publisherJedis.publish(CHANNEL_NAME, "悟空");
                publisherJedis.publish(CHANNEL_NAME, "八戒");
                publisherJedis.publish(CHANNEL_NAME, message);
            }
        }).start();
        publisherJedis.close();
        return "ok";


    }
    @RequestMapping( value = "/subscribe", method = RequestMethod.POST)
    public String subscribeOps(){
        JedisPool JEDIS_POOL=redisConfig.redisPoolFactory();
        Jedis subscriberJedis = JEDIS_POOL.getResource();
        SubThread subThread=new SubThread(JEDIS_POOL,CHANNEL_NAME);
        subThread.start();

        Subscriber subscriber = new Subscriber();
        //订阅线程：接收消息
        new Thread(new Runnable() {
            public void run() {
                try {
                    LOGGER.info("Subscribing to "+CHANNEL_NAME+". This thread will be blocked.");
                    //使用subscriber订阅CHANNEL_NAME上的消息，这一句之后，线程进入订阅模式，阻塞。
                    subscriberJedis.subscribe(subscriber, CHANNEL_NAME);
                    // 订阅得到信息在lister的onMessage(...)方法中进行处理
                    // 订阅多个频道
                    //subscriberJedis.psubscribe(listener, new String[]{"process*"});// 使用模式匹配的方式设置频道

                    //当unsubscribe()方法被调用时，才执行以下代码
                    LOGGER.info("Subscription ended.");
                } catch (Exception e) {
                    LOGGER.error("Subscribing failed.", e);
                }
            }
        }).start();

        //Unsubscribe
        //subscriber.unsubscribe();
        subscriberJedis.close();
        return "ok";
    }



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

