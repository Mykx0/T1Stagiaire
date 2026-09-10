package com.lacouf.rsbjwt.security.exception;

import org.springframework.http.HttpStatus;

public abstract class APIException extends RuntimeException{
	protected final HttpStatus status;
	protected final String message;

	public HttpStatus getStatus() {
		return status;
	}

	public APIException(HttpStatus status, String message){
		this.status = status;
		this.message = message;
	}

	@Override
	public String getMessage(){
		return message;
	}
}
