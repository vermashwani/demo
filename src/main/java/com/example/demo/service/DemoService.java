package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface DemoService {
	
	public List<User> getAllUsers() throws Exception;
	
	public User adduser(User user) throws Exception;

}
