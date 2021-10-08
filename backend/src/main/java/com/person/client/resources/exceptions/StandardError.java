package com.person.client.resources.exceptions;

import java.time.Instant;

import javax.persistence.Column;

public class StandardError {

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant timeStamp;
	private Integer status;
	private String Error;
	private String message;
	private String path;
	
	public StandardError() {
		
	}

	public Instant getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Instant timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return Error;
	}

	public void setError(String error) {
		Error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
}
