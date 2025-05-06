package com.gms.exception;

//Custom exception for handling cases when a resource is not found
public class ResourceNotFoundException extends RuntimeException {

	// Constructor that accepts a message and passes it to the parent class
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
