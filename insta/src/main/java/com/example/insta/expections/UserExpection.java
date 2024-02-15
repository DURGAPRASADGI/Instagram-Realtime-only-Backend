package com.example.insta.expections;

public class UserExpection extends RuntimeException {

	private String message;

	public UserExpection(String message) {
		super(message);
		this.message = message;
	}
}
