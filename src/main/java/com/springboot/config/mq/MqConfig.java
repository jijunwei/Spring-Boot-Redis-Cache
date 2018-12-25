package com.springboot.config.mq;
//package com.dongxu.insurance.config.mq;
//
//import com.dongxu.insurance.service.mq.IMessageHandler;
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PostConstruct;
//
///**
// * lingzihua
// * mq 配置文件
// */
//@Configuration
//public class MqConfig {
//    @Value("${rabbitmq.connection.queue.var1}")
//    private String varOneQueueName;
//    @Value("${rabbitmq.connection.queue.var2}")
//    private String varTwoQueueName;
//    @Value("${rabbitmq.tips.exchange}")
//    private String exchange;
//    @Value("${rabbitmq.connection.host}")
//    private String host;
//    @Value("${rabbitmq.connection.port}")
//    private Integer port;
//    @Value("${rabbitmq.connection.user}")
//    private String user;
//    @Value("${rabbitmq.connection.password}")
//    private String password;
//    @Value("${rabbitmq.connection.virtual-host.tips}")
//    private String virtualHost;
//    private static ConnectionFactory connectionFactory;
//
//    @Autowired
//    @Qualifier("receiveMessageHandler")
//    private IMessageHandler messageHandler;
//
//
//    @PostConstruct
//    public void init() {
//        connectionFactory = connectionFactory();
//    }
//
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
//        connectionFactory.setChannelCacheSize(1024);
//        connectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CONNECTION);
//        connectionFactory.setChannelCacheSize(180 * 1000);
//        connectionFactory.setConnectionCacheSize(1024);
//        connectionFactory.setUsername(user);
//        connectionFactory.setPassword(password);
//        connectionFactory.setVirtualHost(virtualHost);
//        connectionFactory.setPublisherReturns(false);
//        connectionFactory.setPublisherConfirms(false);
//        return connectionFactory;
//    }
//
//    @Bean
//    public AmqpAdmin amqpAdmin() {
//        return new RabbitAdmin(connectionFactory);
//    }
//
//    @Bean
//    public DirectExchange directExchange() {
//        DirectExchange directExchange = new DirectExchange(exchange);
//        return directExchange;
//    }
//
//    @Bean(name = "varOneQueue")
//    public Queue varOneQueue() {
//        return new Queue(varOneQueueName);
//    }
//
//
//    @Bean(name = "varTwoQueue")
//    public Queue varTwoQueue() {
//        return new Queue(varTwoQueueName);
//    }
//
//    @Bean(name = "varOneQBinding")
//    public Binding varOneQBinding(AmqpAdmin amqpAdmin, @Qualifier("varOneQueue") Queue varOneQueue, DirectExchange directExchange) {
//        Binding binding = BindingBuilder.bind(varOneQueue).to(directExchange).with(varOneQueue.getName());
//        amqpAdmin.declareBinding(binding);
//        return binding;
//    }
//
//    @Bean(name = "varTwoQBinding")
//    public Binding varTwoQBinding(AmqpAdmin amqpAdmin, @Qualifier("varTwoQueue") Queue varTwoQueue, DirectExchange directExchange) {
//        Binding binding = BindingBuilder.bind(varTwoQueue).to(directExchange).with(varTwoQueue.getName());
//        amqpAdmin.declareBinding(binding);
//        return binding;
//    }
//
//
//    @Bean(name = "rabbitTemplate")
//    public RabbitTemplate contractTemplate() {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        template.setExchange(exchange);
//        return template;
//    }
//
//    @Bean
//    public SimpleMessageListenerContainer container(@Qualifier("varOneQueue") Queue varOneQueue,
//                                                    @Qualifier("messageAdapter") MessageListenerAdapter listenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueues(varOneQueue);
//        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
//        container.setMessageListener(listenerAdapter);
//        container.start();
//        return container;
//    }
//
//    @Bean(name = "messageHandler")
//    IMessageHandler receiver() {
//        return messageHandler;
//    }
//
//
//    @Bean(name = "messageAdapter")
//    MessageListenerAdapter listenerAdapter(@Qualifier("messageHandler") IMessageHandler messageHandler) {
//        return new MessageListenerAdapter(messageHandler, "handle");
//    }
//}
