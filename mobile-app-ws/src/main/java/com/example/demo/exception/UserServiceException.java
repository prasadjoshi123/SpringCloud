package com.example.demo.exception;

public class UserServiceException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 884173460911598532L;

	public UserServiceException(String message) {
		super(message);
	}

}
