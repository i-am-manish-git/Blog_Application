package com.manish.blog.services;

import java.util.List;

import com.manish.blog.payloads.UserDto;

public interface UserService {
	
	//create new user
	UserDto registerNewUser(UserDto user);
	
	//create
	UserDto createUser(UserDto user);
	
	//update
	UserDto updateUser(UserDto user, Integer userId);
	
	
	//getById
	UserDto getUserById(Integer userId);
	
	//getAll
	List<UserDto> getAllUsers();
	
	
	//delete
	void deleteUser(Integer userId);

}
