package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {

	// Add an exception for CustomerNotFoundException

	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exe) {

		// create CustomerErrorResponse
		CustomerErrorResponse error = new CustomerErrorResponse(HttpStatus.NOT_FOUND.value(), exe.getMessage(),
				System.currentTimeMillis());

		// return response Entity

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}

	// Add Another Exception handler ... to catch any exception. (catch all)
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(Exception exe) {

		// create CustomerErrorResponse
		CustomerErrorResponse error = new CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(), exe.getMessage(),
				System.currentTimeMillis());

		// return response Entity

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}

}
