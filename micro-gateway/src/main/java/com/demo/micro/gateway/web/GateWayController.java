package com.demo.micro.gateway.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GateWayController {
	
	
	 @RequestMapping(value = "/hi", method = RequestMethod.GET)
	 public String hi(){
	    return "hi gateway1!";
	 }
	 
	 
	 @RequestMapping(value = "/aaa/hi", method = RequestMethod.GET)
	 public String hiaaa(){
	    return "hi aaa gateway!";
	 }
	 
	/* 
	 @RequestMapping(value = "/user/hi", method = RequestMethod.GET)
	 public String userhi(){
	    return "hi user !";
	 }
	 
	 
	 @RequestMapping(value = "/api/hi", method = RequestMethod.GET)
	 public String apihi(){
	    return "hi api !";
	 }
	 
	 @RequestMapping(value = "/api/hi", method = RequestMethod.POST)
	 public String apihipost(){
		 System.out.println("1");
	    return "hi api !";
	 }*/
	 
	 

}
