package com.igor.springitemsinstock.rest;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

	private HttpStatus status;
	private String message;
	private long timestamp;
	
	private ErrorResponse(HttpStatus status, String message, long timestamp) {
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
	}
	
	public static ErrorResponse now(HttpStatus status, String message) {
		return new ErrorResponse(status, message, System.currentTimeMillis());
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
