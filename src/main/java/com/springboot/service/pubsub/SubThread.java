package com.springboot.service.pubsub;

import com.springboot.service.pubsub.Subscriber;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public class SubThread extends Thread {
    private final JedisPool jedisPool;
    private final Subscriber subscriber = new Subscriber();

    private  String channel;

    public SubThread(JedisPool jedisPool,String Channel_name) {
        super("SubThread");
        this.jedisPool = jedisPool;
        this.channel=Channel_name;
    }

    @Override
    public void run() {
        //System.out.println(String.format("subscribe redis, channel %s, thread will be blocked", channel));
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.subscribe(subscriber, channel);
        } catch (Exception e) {
            System.out.println(String.format("subsrcibe channel error, %s", e));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}

