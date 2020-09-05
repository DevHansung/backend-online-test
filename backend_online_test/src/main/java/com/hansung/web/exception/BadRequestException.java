package com.hansung.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	private String message;

	public BadRequestException(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
}