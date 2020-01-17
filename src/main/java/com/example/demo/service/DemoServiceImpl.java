package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.DemoServiceRepository;

@Service
public class DemoServiceImpl implements DemoService {
	
	@Autowired
	private DemoServiceRepository demoRepository;
	
	
	@Override
	public List<User> getAllUsers() throws Exception {

		return demoRepository.findAll();
	}
	
    public User adduser(User user) throws Exception{
    	user.setUserAddedDate(new Date());
       
    	demoRepository.save(user);
        return user;
    
}

}
