package com.demo.microuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.demo.microuser"})
@MapperScan("com.demo.microuser.**.dao")
public class MicroUserWebServer {

	
	public static void main(String[] args) {
		SpringApplication.run(MicroUserWebServer.class, args);
	}

}
