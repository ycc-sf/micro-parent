package com.demo.micro.resource;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.demo.micro.api"})
@ComponentScan(basePackages = {"com.demo.micro"})
@MapperScan("com.demo.micro.**.dao")
@EnableHystrix
public class ResourceServer {

	
	public static void main(String[] args) {
		SpringApplication.run(ResourceServer.class, args);

	}

}
