package com.example.insta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.insta.model.Comments;
import com.example.insta.model.User;
import com.example.insta.service.CommentService;
import com.example.insta.service.UserService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create/{postId}")
	public ResponseEntity<Comments> createCommentHandler(@RequestBody Comments comments, @PathVariable Integer postId,@RequestHeader("Authorization") String token){
		User user=userService.findUserProfile(token);
		
		Comments createdComment=commentService.createComment(comments, postId, user.getId());
		return new ResponseEntity<Comments>(createdComment,HttpStatus.OK);
		
	}
	@PutMapping("/like/{commentsId}")
	public ResponseEntity<Comments> likeCommentHandler(@RequestHeader("Authorization") String token,@PathVariable("commentsId") Integer commentId){
		User user=userService.findUserProfile(token);
		
		Comments comments=commentService.likeComments(commentId, user.getId());

		return new ResponseEntity<Comments>(comments,HttpStatus.OK);
		
	}
	
	
	
	@PutMapping("/unLike/{commentsId}")
	public ResponseEntity<Comments> unLikeCommentHandler(@RequestHeader("Authorization") String token,@PathVariable("commentsId") Integer commentId){
		User user=userService.findUserProfile(token);
		
		Comments comments=commentService.likeComments(commentId, user.getId());

		return new ResponseEntity<Comments>(comments,HttpStatus.OK);
		
	}

}
