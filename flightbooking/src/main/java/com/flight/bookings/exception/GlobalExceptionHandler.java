package com.flight.bookings.exception;
import com.flight.bookings.payload.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNoFoundException ex){
		String message =ex.getMessage();
		
		ApiResponse response=ApiResponse.builder().message(message).success(false).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
	
	}
	
	@ExceptionHandler
	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(NotSufficientSeats ex){
		String message =ex.getMessage();
		
		ApiResponse response=ApiResponse.builder().message(message).success(false).status(HttpStatus.FORBIDDEN).build();
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
	
	}
}
