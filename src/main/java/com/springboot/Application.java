package com.springboot;

import com.springboot.config.redis.RedisConfig;
import com.springboot.service.pubsub.SubThread;
import com.springboot.service.pubsub.Subscriber;
import com.springboot.service.socket.SocketServer;
import com.springboot.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.net.Socket;


@SpringBootApplication
@EnableCaching
@ComponentScan(value="com.springboot")
public class Application {
	public static final String CHANNEL_NAME = "thirdPlatformProcessQueue";
	public static final String CHANNEL_NAME_Processing = "thirdPlatformProcessingQueue";
	public static final String CHANNEL_NAME_Processed = "thirdPlatformProcessedQueue";
	public static void main(String[] args) {
		Logger logger= LoggerFactory.getLogger(Application.class);
		SpringApplication.run(Application.class,args);
		SocketServer socketServer=new SocketServer();
		socketServer.startSocketServer(5000);

		RedisConfig redisConfig = SpringUtil.getBean(RedisConfig.class);
		JedisPool JEDIS_POOL=redisConfig.redisPoolFactory();
		Jedis subscriberJedis = JEDIS_POOL.getResource();
		SubThread subThread=new SubThread(JEDIS_POOL,CHANNEL_NAME);
		subThread.start();

		Subscriber subscriber = new Subscriber();
		//订阅线程：接收消息
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					logger.info("Subscribing to "+CHANNEL_NAME+". This thread will be blocked.");
					//使用subscriber订阅CHANNEL_NAME上的消息，这一句之后，线程进入订阅模式，阻塞。
					subscriberJedis.subscribe(subscriber, CHANNEL_NAME);
					// 订阅得到信息在lister的onMessage(...)方法中进行处理
					// 订阅多个频道
					//subscriberJedis.psubscribe(listener, new String[]{"process*"});// 使用模式匹配的方式设置频道

					//当unsubscribe()方法被调用时，才执行以下代码
					logger.info("Subscription ended.");
				} catch (Exception e) {
					logger.error("Subscribing failed.", e);
				}
			}
		}).start();
	}
}
