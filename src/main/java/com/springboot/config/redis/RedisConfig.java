package com.springboot.config.redis;


import com.springboot.service.provider.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(RedisConfig.class);

    @Value("${spring.redis.database}")
    private Long database;

    @Value("${spring.redis.master.host}")
    private String masterHost;
    @Value("${spring.redis.master.port}")
    private int masterPort;
    @Value("${spring.redis.master.name}")
    private String masterName;

    @Value("${spring.redis.slave1.host}")
    private String slave1Host;
    @Value("${spring.redis.slave1.port}")
    private int slave1Port;

    @Value("${spring.redis.slave2.host}")
    private String slave2Host;
    @Value("${spring.redis.slave2.port}")
    private int slave2Port;

    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.pool.max-active}")
    private int max_active;

    @Value("${spring.redis.pool.max-wait}")
    private int max_wait;
    @Value("${spring.redis.pool.max-idle}")
    private int max_idle;
    @Value("${spring.redis.pool.min-idle}")
    private int min_idle;
    @Value("${spring.redis.pool.timeout}")
    private int timeout;


    @Value("${spring.redis.sentinel1.host}")
    private String sentinel1Host;
    @Value("${spring.redis.sentinel1.port}")
    private int sentinel1port;
    @Value("${spring.redis.sentinel2.host}")
    private String sentinel2Host;
    @Value("${spring.redis.sentinel2.port}")
    private int sentinel2port;
    @Value("${spring.redis.sentinel3.host}")
    private String sentinel3Host;
    @Value("${spring.redis.sentinel3.port}")
    private int sentinel3port;
    @Value("${spring.countDownLatch}")
    private int latch;
    @Autowired
    private RedisProperties redisProperties;
    public JedisPool jedisPool;
    // 自定义缓存key生成策略
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, java.lang.reflect.Method method, Object... params) {
                StringBuffer sb = new StringBuffer();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    // 缓存管理器
    @Bean
    public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        // 设置缓存过期时间
        cacheManager.setDefaultExpiration(10000);
        return cacheManager;
    }
/*
    @Bean
    public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        setSerializer(template);// 设置序列化工具
        template.afterPropertiesSet();
        return template;
    }*/
    @Bean
    public RedisTemplate<String, Object> redisTemplate2(RedisConnectionFactory redisConnectionFactory)
    {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(jackson2JsonRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }





    @Bean
    Receiver receiver(CountDownLatch latch) {
        return new Receiver(latch);
    }

    //必要的redis消息队列连接工厂
    @Bean
    CountDownLatch latch() {
        return new CountDownLatch(latch);
    }

    //redis模板
    @Bean("stringRedisTemplate")
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    //注入消息监听器容器
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("msg"));
        return container;
    }

    //注入消息监听器容器
    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");

    }



    @ConfigurationProperties(prefix="spring.redis")
    private JedisConnectionFactory generateConnectionFactory() {

        RedisSentinelConfiguration sentinelConfiguration = new RedisSentinelConfiguration();
        RedisNode master = new RedisNode(masterHost, masterPort);
        master.setName(masterName);

        RedisNode slave1 = new RedisNode(slave1Host, slave1Port);
        RedisNode slave2 = new RedisNode(slave2Host, slave2Port);

        Set<RedisNode> sentinels = new HashSet<>();
        RedisNode sentinel1 = new RedisNode(sentinel1Host, sentinel1port);
        RedisNode sentinel2 = new RedisNode(sentinel2Host, sentinel2port);
        RedisNode sentinel3 = new RedisNode(sentinel3Host, sentinel3port);
        sentinels.add(sentinel1);
        sentinels.add(sentinel2);
        sentinels.add(sentinel3);
        sentinelConfiguration.setSentinels(sentinels);
        sentinelConfiguration.setMaster(master);

        //JedisPoolConfig poolConfig = generatePoolConfig();
        JedisPoolConfig poolConfig=jedisPoolConfig();
        JedisConnectionFactory factory = new JedisConnectionFactory(sentinelConfiguration, poolConfig);
        //JedisConnectionFactory factory=new JedisConnectionFactory(poolConfig);
        factory.setHostName(masterHost);
        factory.setPort(masterPort);
        factory.setTimeout(10000);
        factory.setUsePool(true);
        factory.setPassword(password);
        factory.setConvertPipelineAndTxResults(true);
        factory.setPoolConfig(poolConfig);
        factory.afterPropertiesSet();
        return factory;
    }

    @Bean(name = "jedis")
    public Jedis getRedisInstance(@Qualifier("redisConnectionFactory")JedisConnectionFactory jedisConnectionFactory) {
        Jedis jedis = (Jedis) jedisConnectionFactory.getConnection()
                .getNativeConnection();
        return jedis;
    }

    @Bean(name = "redisConnectionFactory")
    JedisConnectionFactory factory() {
        LOGGER.info("初始化redis配置开始");

        return generateConnectionFactory();

    }
    @Bean
    public JedisPool redisPoolFactory() {
        LOGGER.info("JedisPool注入成功！！");
        LOGGER.info("redis地址：" + masterHost + ":" + masterPort);


        if(jedisPool==null) {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxIdle(max_idle);
            jedisPoolConfig.setMaxWaitMillis(max_wait);
            jedisPool= new JedisPool(jedisPoolConfig, masterHost, masterPort, timeout, password);
        }
        return jedisPool;
    }
    /**
     * 设置数据存入 redis 的序列化方式
     *
     * @param redisTemplate
     * @param factory
     */
    private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(factory);
    }

    /**
     * 实例化 HashOperations 对象,可以使用 Hash 类型操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    /**
     * 实例化 ValueOperations 对象,可以使用 String 操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    /**
     * 实例化 ListOperations 对象,可以使用 List 操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    /**
     * 实例化 SetOperations 对象,可以使用 Set 操作
     *
     //* @param redisTemplate
     * @return
     */
    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    /**
     * 实例化 ZSetOperations 对象,可以使用 ZSet 操作
     *
     //* @param redisTemplate
     * @return
     */
    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }
    @Bean
    @ConfigurationProperties(prefix="spring.redis.pool")
    public JedisPoolConfig jedisPoolConfig() {
        return new JedisPoolConfig();
    }
    private JedisPoolConfig generatePoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMinIdle(min_idle);
        poolConfig.setMaxTotal(max_active);
        poolConfig.setMaxWaitMillis(max_wait);
        poolConfig.setTestOnBorrow(true);

        return poolConfig;
    }
    private void setSerializer(StringRedisTemplate template) {
        @SuppressWarnings({ "rawtypes", "unchecked" })
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
    }
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
