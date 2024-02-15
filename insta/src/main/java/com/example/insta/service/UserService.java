package com.example.insta.service;

import java.util.List;

import com.example.insta.expections.UserExpection;
import com.example.insta.model.User;
 
public interface UserService {

	public User registerUser(User user) throws UserExpection;
	public User findUserById(Integer UserId) throws UserExpection;
	public User findUserProfile(String token) throws UserExpection;
	public User findUserByUsername(String username) throws UserExpection;
	public String followUser(Integer reqUserId,Integer followUserId) throws UserExpection;
	public String unFollowUser(Integer reqUserId,Integer followUserId) throws UserExpection;
	public List<User> findUserByIds(List<Integer> userIds) throws UserExpection;
	public List<User> searchUser(String query) throws UserExpection;
	public User updateUserDetails(User updatedUser,User existingUser) throws UserExpection;


}
