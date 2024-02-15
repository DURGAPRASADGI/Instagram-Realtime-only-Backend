package com.example.insta.expections;

public class StoryExpection extends RuntimeException {
	private String message;

	public StoryExpection(String message) {
		super(message);
		this.message = message;
	}

}
