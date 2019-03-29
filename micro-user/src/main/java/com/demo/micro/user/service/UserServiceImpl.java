package com.demo.micro.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.micro.api.resource.ResourceServiceApi;
import com.demo.micro.user.dao.UserDao;

//import com.demo.micro.api.authorization.AuthorizationService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao accountDao;
	@Autowired
	private ResourceServiceApi resourceService;

}
