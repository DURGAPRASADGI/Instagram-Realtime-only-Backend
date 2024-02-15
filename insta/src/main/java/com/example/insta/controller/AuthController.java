package com.example.insta.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.insta.model.User;
import com.example.insta.repository.UserRepository;
import com.example.insta.service.UserService;

@RestController
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	 
	@PostMapping("/signup")
	public ResponseEntity<User> registerUserHandler(@RequestBody User user){
		User createUser=userService.registerUser(user);
		return new ResponseEntity<User>(createUser,HttpStatus.CREATED);
	}
	
	@GetMapping("/signin")
	public ResponseEntity<User> siginUserHandler(Authentication auth){
		String email=auth.getName();
		Optional<User> opt=userRepository.findByEmail(email);
		if(opt.isPresent()) {
			return new ResponseEntity<User>(opt.get(),HttpStatus.ACCEPTED);
		}
		throw new BadCredentialsException("invalid username or password");
		
	}
	
	

}
