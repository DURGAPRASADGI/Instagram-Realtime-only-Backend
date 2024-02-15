package com.example.insta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.insta.model.Post;
import com.example.insta.model.User;
import com.example.insta.service.PostService;
import com.example.insta.service.UserService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<Post> createHandler(@RequestBody Post post,@RequestHeader("Authorization") String token){
		User user=userService.findUserProfile(token);
		Post createdPost=postService.createPost(post, user.getId());
		return new ResponseEntity<Post>(createdPost,HttpStatus.OK);
		
	}
	
	@GetMapping("/all/{userId}")
	public ResponseEntity<List<Post>> findPostByUserHandlier(@PathVariable Integer userId){
		List<Post> posts=postService.findUserById(userId);
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
		
	}
	
	@GetMapping("/following/{userIds}")
	public ResponseEntity<List<Post>> findAllPostByUserHandlier(@PathVariable List<Integer> userIds){
		List<Post> posts=postService.findPostByUserIds(userIds);
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
		
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<Post> findByIdHandler(@PathVariable Integer postId){
		Post post=postService.findPostById(postId);
		return new ResponseEntity<Post>(post,HttpStatus.OK);
		
	}
	
	@PutMapping("/like/{postId}")
	public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId,@RequestHeader("Authorization")  String token){
		User user=userService.findUserProfile(token);
		Post post=postService.likePost(postId, user.getId());
		return new ResponseEntity<Post>(post,HttpStatus.OK);
		
	}
	@PutMapping("/unlike/{postId}")
	public ResponseEntity<Post> unLikePostHandler(@PathVariable Integer postId,@RequestHeader("Authorization")  String token){
		User user=userService.findUserProfile(token);
		Post post=postService.unLikePost(postId, user.getId());
		return new ResponseEntity<Post>(post,HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/delete/{postId}")
	public ResponseEntity<String> deletePostHandler(@PathVariable Integer postId,@RequestHeader("Authorization")  String token){
		User user=userService.findUserProfile(token);
		String Message=postService.deletePost(postId, user.getId());

		return new ResponseEntity<String>(Message,HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/savePost/{postId}")
	public ResponseEntity<String> savedPostHandler(@PathVariable Integer postId,@RequestHeader("Authorization") String token){
		User user=userService.findUserProfile(token);
        String Message=postService.savedPost(postId, user.getId());
		return new ResponseEntity<String>(Message,HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/unSavePost/{postId}")
	public ResponseEntity<String> unSavedPostHandler(@PathVariable   Integer postId,@RequestHeader("Authorization") String token){
		User user=userService.findUserProfile(token);
        String Message=postService.unSavedPost(postId, user.getId());
		return new ResponseEntity<String>(Message,HttpStatus.ACCEPTED);
		
	}
	

}
