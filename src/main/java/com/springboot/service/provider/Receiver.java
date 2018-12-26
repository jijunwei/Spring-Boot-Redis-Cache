package com.springboot.service.provider;


import com.springboot.bean.Student;
import com.springboot.service.StudentService;
import com.springboot.util.SpringUtil;
import com.springboot.util.XmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

public class Receiver {
    Logger log=LoggerFactory.getLogger(Receiver.class);
    private CountDownLatch latch;

    @Autowired
    private StudentService service;
    public Receiver(CountDownLatch latch) {
        this.latch = latch;
    }

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");

        //异步写入

        log.info("start.....");
        Object m= XmlUtil.xmlToBean2(message,Student.class);

        log.info(m.toString()+"add success end...");
        service.add((Student ) m);
        latch.countDown();
    }
}

