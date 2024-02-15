package com.example.insta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.insta.model.User;
import com.example.insta.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
	@GetMapping("/id/{id}")
	public ResponseEntity<User> findUserByIdHandler(@PathVariable Integer id){
		User user=userService.findUserById(id);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}

	@GetMapping("/username/{username}")
	public ResponseEntity<User> findUserByUsernameHandler(@PathVariable String username){
		User user=userService.findUserByUsername(username);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@GetMapping("/m/{userIds}")
	public ResponseEntity<List<User>> findUserByUserIdsHandler(@PathVariable List<Integer> userIds){
	List<User>users=userService.findUserByIds(userIds);
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
		
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<User>> searchHandler(@RequestParam("q") String query){
		List<User>users=userService.searchUser(query);
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
		
	}
	
	@PutMapping("follow/{followUserId}")
	public ResponseEntity<String> followUserHandler(@PathVariable Integer followUserId,@RequestHeader("Authorization") String token){
		User user=userService.findUserProfile(token);
        String message=userService.followUser(user.getId(), followUserId);
		return new ResponseEntity<String>(message,HttpStatus.OK);
		
	}
	
	
	@PutMapping("unFollow/{followUserId}")
	public ResponseEntity<String> unFollowUserHandler(@PathVariable Integer followUserId,@RequestHeader("Authorization") String token){
		User user=userService.findUserProfile(token);
        String message=userService.followUser(user.getId(), followUserId);
		return new ResponseEntity<String>(message,HttpStatus.OK);
		
	}
	
	@PutMapping("/reg")
	public ResponseEntity<User> findUserProfileHandler(@RequestHeader("Authorization") String token){
		User user=userService.findUserProfile(token);

		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
	
	@PutMapping("/account/edit")
	public ResponseEntity<User> updateUserHandler(@RequestHeader("Authorization") String token,@RequestBody User user){
		User requser=userService.findUserProfile(token);
		
		User updatedUser=userService.updateUserDetails(user, requser);

		return new ResponseEntity<User>(updatedUser,HttpStatus.OK);
		
	}
	
	
}
