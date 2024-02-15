package com.example.insta.service;

import java.util.List;

import com.example.insta.expections.PostExpection;
import com.example.insta.expections.UserExpection;
import com.example.insta.model.Post;

public interface PostService {
	
public Post createPost (Post post,Integer userId) throws UserExpection;

public String deletePost(Integer postId,Integer userId) throws UserExpection,PostExpection;

public List<Post> findUserById(Integer userId) throws UserExpection;

public Post findPostById(Integer postId) throws PostExpection;

public List<Post> findPostByUserIds(List<Integer> userIds) throws PostExpection,UserExpection;

public String savedPost(Integer postId,Integer UserId) throws PostExpection,UserExpection;

public String unSavedPost(Integer postId,Integer UserId) throws PostExpection,UserExpection;

public Post likePost(Integer postId,Integer userId) throws PostExpection,UserExpection;

public Post unLikePost(Integer postId,Integer userId) throws PostExpection,UserExpection;


	
	

}
