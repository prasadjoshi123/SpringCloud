package com.example.demo.model;

import java.util.Date;

public class ErrorMessage {
	private java.util.Date timestamp;
	private String Message;

	public ErrorMessage() {
		super();
	}

	public ErrorMessage(Date timestamp, String message) {
		super();
		this.timestamp = timestamp;
		Message = message;
	}

	public java.util.Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(java.util.Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}
}
