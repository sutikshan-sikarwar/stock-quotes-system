package com.thinkhumble.service;


import com.thinkhumble.models.User;

public interface UserService {
	
	public User registerUser(User user);
	
	public User findUserByJwt(String jwt);
	
	public User updateUser(User user, Integer userId) throws Exception;

}
