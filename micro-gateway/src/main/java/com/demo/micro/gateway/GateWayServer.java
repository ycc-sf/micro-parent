package com.demo.micro.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.demo.micro.gateway.filter.PreFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.demo.micro"})
public class GateWayServer {

	
	public static void main(String[] args) {
		SpringApplication.run(GateWayServer.class, args);

	}
	

	@Bean
	public PreFilter preFileter() {
		return new PreFilter();
	}

}
