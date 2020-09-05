package com.hansung.web.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hansung.web.dto.ApiResponse;
import com.hansung.web.exception.BadRequestException;
import com.hansung.web.exception.UnauthorizedException;

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<?> unauthorizedExceptionHandler(UnauthorizedException e) {
		return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> badRequestExceptionHandler(BadRequestException e) {
		return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exceptionHandler(Exception e) {
		String message = "처리 도중 문제가 발생하였습니다..";
		return ResponseEntity.badRequest().body(new ApiResponse(false, message));
	}
}