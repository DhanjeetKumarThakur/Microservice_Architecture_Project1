package com.learningMS.user.service.exception;

public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException() {
		super("Resources Not Found");
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
