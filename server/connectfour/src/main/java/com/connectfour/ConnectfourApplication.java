package com.connectfour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.connectfour")
public class ConnectfourApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectfourApplication.class, args);
	}

}
