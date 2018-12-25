package com.springboot.service.provider;


import java.util.concurrent.CountDownLatch;

public class Receiver {
    private CountDownLatch latch;

    public Receiver(CountDownLatch latch) {
        this.latch = latch;
    }

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }
}

