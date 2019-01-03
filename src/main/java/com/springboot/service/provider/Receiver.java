package com.springboot.service.provider;


import com.springboot.model.bean.Student;
import com.springboot.model.route.SourceMsg;
import com.springboot.service.StudentService;
import com.springboot.service.platform.SourceMsgService;
import com.springboot.service.route.RouteService;
import com.springboot.util.JaXmlBeanUtil;
import com.springboot.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

public class Receiver {
    Logger log=LoggerFactory.getLogger(Receiver.class);
    private CountDownLatch latch;

    @Autowired
    private StudentService service;
    @Autowired
    private RouteService routeService;
    @Autowired
    private SourceMsgService sourceMsgService;
    public Receiver(CountDownLatch latch) {
        this.latch = latch;
    }

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");

        /*HSChannelService hsChannelService = SpringUtil.getBean(HSChannelService.class);
                    String respxml =  hsChannelService.handle(reqxml);*/


        //异步写入
        StudentService studentService = SpringUtil.getBean(StudentService.class);
        SourceMsg sourceMsg = (SourceMsg) JaXmlBeanUtil.parseXmlToBean(SourceMsg.class,message);
        /*String studentxml = sourceMsg.getMsg();
        Student student=(Student) JaXmlBeanUtil.parseXmlToBean(Student.class,studentxml);
        int i = studentService.add(student);*/
        int i=sourceMsgService.add(sourceMsg);
        String result;
        if (i == 1) {
            result = " platform add into db success!";
        } else {
            result = "platform fail!";
        }


        latch.countDown();



    }
}

