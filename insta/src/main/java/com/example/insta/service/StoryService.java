package com.example.insta.service;

import java.util.List;

import com.example.insta.expections.StoryExpection;
import com.example.insta.expections.UserExpection;
import com.example.insta.model.Story;

public interface StoryService {

	public Story cerateStory(Story story,Integer userId) throws UserExpection;
	
	public List<Story> findStoryByUserId(Integer userId) throws UserExpection,StoryExpection;
}
