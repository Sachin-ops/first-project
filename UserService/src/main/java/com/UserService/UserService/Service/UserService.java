package com.UserService.UserService.Service;

import java.util.List;

import com.UserService.UserService.Entity.UserEntity;

public interface UserService {

	
	UserEntity getUser(long userId);
	UserEntity updateUser(long userId, UserEntity userEntity);
	UserEntity createUser(UserEntity userEntity);
	UserEntity deleteUser(long userId);
	List<UserEntity>getAllUser();
//	UserEntity saveUser(UserEntity user);
}
