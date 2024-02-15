package com.example.insta.expections;

public class CommentExpection extends RuntimeException {
	private String message;

	public CommentExpection(String message) {
		super(message);
		this.message = message;
	}

}
