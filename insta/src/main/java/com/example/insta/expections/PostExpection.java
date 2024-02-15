package com.example.insta.expections;

public class PostExpection extends RuntimeException {

	private String message;

	public PostExpection(String message) {
		super(message);
		this.message = message;
	}
	
}
