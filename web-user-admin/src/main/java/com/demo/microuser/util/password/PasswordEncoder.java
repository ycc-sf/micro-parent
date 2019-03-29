package com.demo.microuser.util.password;

public interface PasswordEncoder {
	/**
	 * @param1 password
	 * @param2 salt
	 * @return String
	 * @date 2018年09月04日 10:44:42
	 */
	String encode(String password, String ...salt);

}
