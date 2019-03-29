package com.demo.micro.user.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.demo.micro.user.common.cache.Cache;
import com.demo.micro.user.common.cache.RedisCache;

@Configuration
public class RedisConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);
	
	@Bean
	public Cache cache(StringRedisTemplate redisTemplate){
		return new RedisCache(redisTemplate);
	}
	

}
