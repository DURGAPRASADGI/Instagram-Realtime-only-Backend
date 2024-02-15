package com.example.insta.service;

import com.example.insta.expections.CommentExpection;
import com.example.insta.expections.PostExpection;
import com.example.insta.expections.UserExpection;
import com.example.insta.model.Comments;

public interface CommentService {
	public Comments createComment(Comments comments,Integer postId,Integer userId ) throws UserExpection,PostExpection;
	
	public Comments findCommentById(Integer commentsId) throws CommentExpection;
	
	public Comments likeComments(Integer commentsId,Integer userId) throws CommentExpection,UserExpection;
	
	public Comments unLikeComments(Integer commentsId,Integer userId) throws CommentExpection,UserExpection;

	

}
