package com.example.insta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.insta.model.Story;
import com.example.insta.model.User;
import com.example.insta.service.StoryService;
import com.example.insta.service.UserService;

@RestController
@RequestMapping("/api/stories")
public class StoryController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StoryService storyService;
	
	@PostMapping("/create")
	public ResponseEntity<Story> createStoryHandler(@RequestBody Story story,@RequestHeader("Authorization") String token){
		User user=userService.findUserProfile(token);
		Story createdStory=storyService.cerateStory(story, user.getId());
		return new ResponseEntity<Story>(createdStory,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<Story>> findAllStoriesByUserId(@PathVariable Integer userId){
		User user=userService.findUserById(userId);
		List<Story> stories=storyService.findStoryByUserId(user.getId());
		return new ResponseEntity<List<Story>>(stories,HttpStatus.OK);
		
	}

}
