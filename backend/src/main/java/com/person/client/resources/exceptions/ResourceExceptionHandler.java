package com.person.client.resources.exceptions;

import java.net.http.HttpRequest;
import java.time.Instant;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.person.client.services.exceptions.DatabaseIntegrityException;
import com.person.client.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound (ResourceNotFoundException e, HttpServletRequest request){
		StandardError err = new StandardError();
		HttpStatus http = HttpStatus.NOT_FOUND;
		err.setTimeStamp(Instant.now());
		err.setStatus(http.value());
		err.setError("Resource not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(http).body(err);
	
	}
	
	@ExceptionHandler(DatabaseIntegrityException.class)
	public ResponseEntity<StandardError> entityNotFound (DatabaseIntegrityException e, HttpServletRequest request){
		StandardError err = new StandardError();
		HttpStatus http = HttpStatus.BAD_REQUEST;
		err.setTimeStamp(Instant.now());
		err.setStatus(http.value());
		err.setError("Database integrity violation!");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(http).body(err);
	
	}
	
}
