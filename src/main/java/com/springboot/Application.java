package com.springboot;

import com.springboot.service.socket.SocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.net.Socket;


@SpringBootApplication
@EnableCaching
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class,args);
		SocketServer socketServer=new SocketServer();
		socketServer.startSocketServer(5000);
	}
}
