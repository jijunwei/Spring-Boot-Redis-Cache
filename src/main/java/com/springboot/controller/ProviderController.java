package com.springboot.controller;


import com.springboot.model.bean.JaBeanToXml;
import com.springboot.model.bean.Student;
import com.springboot.config.redis.RedisConfig;
import com.springboot.model.route.RouteMsg;
import com.springboot.service.pubsub.MyListener;
import com.springboot.util.JaXmlBeanUtil;
import groovy.util.logging.Log4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

@Log4j
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
    public String testProvider(@RequestBody String message) {
        try {
            //1.建立TCP连接
            String ip = "localhost";   //服务器端ip地址
            int port = 5000;        //端口号
            Socket sck = new Socket(ip, port);
            //2.传输内容
            //first method
        /*Req2001 req2001=new Req2001();
        req2001.setIdCard("2004101510117");
        req2001.setMobile("18519121911");
        req2001.setName("test");
        req2001.setServiceId("2001");
        String content= XmlUtil.beanToXml(req2001,Req2001.class);*/
            //second method
            Student student = new Student();
            student.setSno("005");
            student.setSex("male");
            student.setName("test");
            String studentxml= JaXmlBeanUtil.parseBeanToXml(Student.class,student);
            RouteMsg routeMsg = new RouteMsg();
            routeMsg.setChannelCode("HS116");
            routeMsg.setChannelSeq("T200000120130909000000001234");
            routeMsg.setChannelDate("20181226");
            routeMsg.setChannelTime("102109");
            routeMsg.setMsg(studentxml);

            //String content=XmlUtil.beanToXml(routeMsg,RouteMsg.class);
            String content= JaXmlBeanUtil.parseBeanToXml(RouteMsg.class,routeMsg);


            //String content = "test message!";
            byte[] bstream = content.getBytes("GBK");  //转化为字节流
            OutputStream os = sck.getOutputStream();   //输出流
            os.write(bstream);
            //3.关闭连接
            sck.close();
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        System.out.println("我要发送消息咯...");
        template.convertAndSend("msg", message);
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

