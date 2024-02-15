package com.example.insta.expections;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GobalExpections {
	@ExceptionHandler(UserExpection.class)
	public ResponseEntity<ErrorDetails> UserExpectionHandler(UserExpection ue,WebRequest  req){
		ErrorDetails err=new ErrorDetails(ue.getMessage(),req.getDescription(false),LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
	}
	
	
	
	
	@ExceptionHandler(PostExpection.class)
	public ResponseEntity<ErrorDetails> PostExpectionHandler(PostExpection ue,WebRequest  req){
		ErrorDetails err=new ErrorDetails(ue.getMessage(),req.getDescription(false),LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(CommentExpection.class)
	public ResponseEntity<ErrorDetails> CommentsExpectionHandler(CommentExpection ue,WebRequest  req){
		ErrorDetails err=new ErrorDetails(ue.getMessage(),req.getDescription(false),LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(StoryExpection.class)
	public ResponseEntity<ErrorDetails> StoryExpectionHandler(StoryExpection ue,WebRequest  req){
		ErrorDetails err=new ErrorDetails(ue.getMessage(),req.getDescription(false),LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,  WebRequest req){
	
		ErrorDetails err=new ErrorDetails(ex.getBindingResult().getFieldError().getDefaultMessage(),req.getDescription(false),LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> OtherExpectionsHandler(UserExpection ue,WebRequest  req){
		ErrorDetails err=new ErrorDetails(ue.getMessage(),req.getDescription(false),LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
	}
}
