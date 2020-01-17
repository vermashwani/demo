package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.DemoService;

@RestController
@RequestMapping("/demo")
public class DemoController {
	
	@Autowired
	private DemoService demoService;
	
	@GetMapping("/welcome")
	public String message(){
		return "Welcome :: Spring Boot application...";
	}
	
	@GetMapping("/users")
	public ResponseEntity<?> viewRecommendedArticles() {
		ResponseEntity<?> responseEntity = null;
		try {
			List<User> users = demoService.getAllUsers();
			if (!users.isEmpty()) {
				responseEntity = new ResponseEntity<>(users, HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<>("No users Found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}
	
	@PostMapping("/adduser")
	public ResponseEntity<?> registerUser(@RequestParam(name = "developer", required = true) String userId){
        ResponseEntity<?> responseEntity = null;
        User user = new User();
        user.setUserId(userId);
        user.setFirstName("Post Commit Testing");
        user.setLastName("Build Hook");
        user.setUserAddedDate(new Date());
		try {
			if(user.getUserId().isEmpty()) {
				responseEntity = new ResponseEntity<String>("User ID can not be empty", HttpStatus.BAD_REQUEST);
			}
			else {
				demoService.adduser(user);
            responseEntity = new ResponseEntity<String>("User added successfully", HttpStatus.CREATED);
			}
	} catch (Exception ex) {
		responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
        return responseEntity;
	}
}
