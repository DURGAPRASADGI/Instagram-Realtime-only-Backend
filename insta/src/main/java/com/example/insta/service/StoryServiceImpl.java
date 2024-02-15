package com.example.insta.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.insta.dto.UserDto;
import com.example.insta.expections.StoryExpection;
import com.example.insta.expections.UserExpection;
import com.example.insta.model.Story;
import com.example.insta.model.User;
import com.example.insta.repository.StoryRepository;
import com.example.insta.repository.UserRepository;

@Service
public class StoryServiceImpl implements StoryService{
	
	@Autowired
	private StoryRepository storyRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Story cerateStory(Story story, Integer userId) throws UserExpection {
		// TODO Auto-generated method stub
		User user=userService.findUserById(userId);
		UserDto userDto=new UserDto();
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setUserImage(user.getImage());
		userDto.setUsername(user.getUsername());
		
	     story.setTimestamp(LocalDateTime.now());
	     story.setUser(userDto);
	     
	     user.getStories().add(story);
	     Story createdStory=storyRepository.save(story);
	     
	       
		
		
		
		return createdStory;
	}

	@Override
	public List<Story> findStoryByUserId(Integer userId) throws UserExpection, StoryExpection {
		// TODO Auto-generated method stub
		User user=userService.findUserById(userId);
		List<Story> stories=user.getStories();
		if(stories.size()==0) {
			throw new StoryExpection("story has exist");
		}
		return stories;
	}

}
