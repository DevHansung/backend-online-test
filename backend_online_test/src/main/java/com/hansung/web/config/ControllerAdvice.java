package com.hansung.web.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.hansung.web.dto.ApiRes;
import com.hansung.web.exception.BadRequestException;
import com.hansung.web.exception.UnauthorizedException;

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<?> unauthorizedExceptionHandler(UnauthorizedException e) {
		return ResponseEntity.badRequest().body(new ApiRes(false, e.getMessage()));
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> badRequestExceptionHandler(BadRequestException e) {
		return ResponseEntity.badRequest().body(new ApiRes(false, e.getMessage()));
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exceptionHandler(Exception e) {
		String message = "처리 도중 문제가 발생하였습니다.";
		return ResponseEntity.badRequest().body(new ApiRes(false, message));
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> exceptionHandler(MethodArgumentTypeMismatchException e) {
		String message = "잘못된 값이 전달되었습니다.";
		return ResponseEntity.badRequest().body(new ApiRes(false, message));
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> exceptionHandler(NullPointerException e) {
		String message = "값이 들어있지 않거나 입력 형태에 맞지 않습니다.";
		return ResponseEntity.badRequest().body(new ApiRes(false, message));
	}
	
	@ExceptionHandler(MismatchedInputException.class)
	public ResponseEntity<?> exceptionHandler(MismatchedInputException e) {
		String message = "잘못된 형태의값이 전달되었습니다.";
		return ResponseEntity.badRequest().body(new ApiRes(false, message));
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> exceptionHandler(IllegalArgumentException e) {
		String message = "입력값의 내부 요소 중 입력 형태와 맞지않는 값이 포함되어있습니다.";
		return ResponseEntity.badRequest().body(new ApiRes(false, message));
	}
}

