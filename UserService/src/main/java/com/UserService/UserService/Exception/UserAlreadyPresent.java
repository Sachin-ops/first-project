package com.UserService.UserService.Exception;

import org.apache.catalina.User;

public class UserAlreadyPresent extends RuntimeException {

	public UserAlreadyPresent(String msg)
	{
		super(msg);
	}
}
