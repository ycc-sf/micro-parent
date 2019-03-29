package com.demo.microuser.util.password;

import com.demo.microuser.util.MD5Util;

public class UsernameAndPasswordMD5PasswordEncoder implements PasswordEncoder {

	
	@Override
	public String encode(String password, String... salt) {
		
		if(salt == null){
			throw new RuntimeException("salt为空");
		}
		System.out.println("salt:" + salt + "  ->" +salt.length);
		if(salt.length != 1){
		    throw new RuntimeException("期望的salt为用户名");
		}
		
		return MD5Util.string2MD5(salt[0] + password);
	}

}
