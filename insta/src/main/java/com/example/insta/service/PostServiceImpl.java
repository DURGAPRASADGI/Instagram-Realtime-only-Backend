package com.example.insta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.insta.dto.UserDto;
import com.example.insta.expections.PostExpection;
import com.example.insta.expections.UserExpection;
import com.example.insta.model.Post;
import com.example.insta.model.User;
import com.example.insta.repository.PostRepository;
import com.example.insta.repository.UserRepository;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	public PostRepository postRepository;
	
	@Autowired
	public UserRepository userRepository;

	@Autowired
	public UserService userService;
	
	@Override
	public Post createPost(Post post,Integer userId) throws UserExpection {
		// TODO Auto-generated method stub
		User user=userService.findUserById(userId);
		UserDto userDto=new UserDto();
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setUserImage(user.getImage());
		userDto.setUsername(user.getUsername());
		post.setUser(userDto);
		Post createdPost=postRepository.save(post);
		return createdPost ;
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws UserExpection, PostExpection {
		// TODO Auto-generated method stub
		Post post=findPostById(postId);
		User user=userService.findUserById(userId);
		if(post.getUser().getId().equals(user.getId())) {
			
			 postRepository.deleteById(post.getId());
			 return "post has deleted";
		}
		
		throw new PostExpection("ypu cannot delete others posts");
		
	}

	@Override
	public List<Post> findUserById(Integer userId) throws UserExpection {
		// TODO Auto-generated method stub
		List<Post> posts=postRepository.findByUserId(userId);
		if(posts.size()==0) {
			throw new UserExpection("post does not exist in user");
		}
		return posts;
	}

	@Override
	public Post findPostById(Integer postId) throws PostExpection {
		// TODO Auto-generated method stub
		Optional<Post> optional=postRepository.findById(postId);
		if(optional.isPresent()) {
			return optional.get();
		}
		throw new PostExpection("post not found");
	}

	@Override
	public List<Post> findPostByUserIds(List<Integer> userIds) throws PostExpection, UserExpection {
		// TODO Auto-generated method stub
		List<Post> posts=postRepository.findAllPostByUserIds(userIds);
		if(posts.size()==0) {
			throw new PostExpection("post not exist"); 
		}
		return posts;
	}

	@Override
	public String savedPost(Integer postId, Integer UserId) throws PostExpection, UserExpection {
		// TODO Auto-generated method stub
		User  user=userService.findUserById(UserId);
		Post post=findPostById(postId);
		if(!user.getSavedPost().contains(post)) {
		     user.getSavedPost().add(post);
		     userRepository.save(user);
			
		}
		
		return "saved post successfully";
	}

	@Override
	public String unSavedPost(Integer postId, Integer UserId) throws PostExpection, UserExpection {
		// TODO Auto-generated method stub
		User  user=userService.findUserById(UserId);
		Post post=findPostById(postId);
		if(!user.getSavedPost().contains(post)) {
		     user.getSavedPost().remove(post);
		     userRepository.save(user);
			
		}
		return "unsaved post successfully";
	}

	@Override
	public Post likePost(Integer postId, Integer userId) throws PostExpection, UserExpection {
		// TODO Auto-generated method stub
		User user=userService.findUserById(userId);
	     UserDto userDto=new UserDto();
	     userDto.setEmail(user.getEmail());
	     userDto.setId(user.getId());
	     userDto.setName(user.getName());
	     userDto.setUserImage(user.getImage());
	     userDto.setUsername(user.getUsername());
	     Post post=findPostById(postId);
	     post.getLikedByUser().add(userDto);
		
		return postRepository.save(post);
	}

	@Override
	public Post unLikePost(Integer postId, Integer userId) throws PostExpection, UserExpection {
		// TODO Auto-generated method stub
		User user=userService.findUserById(userId);
	     UserDto userDto=new UserDto();
	     userDto.setEmail(user.getEmail());
	     userDto.setId(user.getId());
	     userDto.setName(user.getName());
	     userDto.setUserImage(user.getImage());
	     userDto.setUsername(user.getUsername());
	     Post post=findPostById(postId);
	     post.getLikedByUser().remove(userDto);
		
		return postRepository.save(post);	}

}
