package com.sunBase.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetail> notFoundExceptionHandler(NoHandlerFoundException nhfe, WebRequest req){
		MyErrorDetail myError= new MyErrorDetail();
		myError.setTimeStamp(LocalDateTime.now());
		myError.setMessage(nhfe.getMessage());
		myError.setDetails(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetail>(myError, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetail> allExceptionHandler(Exception e, WebRequest req){
		MyErrorDetail myError= new MyErrorDetail();
		myError.setTimeStamp(LocalDateTime.now());
		myError.setMessage(e.getMessage());
		myError.setDetails(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetail>(myError, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(TokenException.class)
	public ResponseEntity<MyErrorDetail> customerExceptionHandlar(TokenException tokenException,WebRequest webRequest){
		MyErrorDetail myError= new MyErrorDetail();
		myError.setTimeStamp(LocalDateTime.now());
		myError.setMessage(tokenException.getMessage());
		myError.setDetails(webRequest.getDescription(false));
		
		return new ResponseEntity<MyErrorDetail>(myError, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyErrorDetail> customerExceptionHandlar(CustomerException customerAddresssException,WebRequest webRequest){
		MyErrorDetail myError= new MyErrorDetail();
		myError.setTimeStamp(LocalDateTime.now());
		myError.setMessage(customerAddresssException.getMessage());
		myError.setDetails(webRequest.getDescription(false));
		
		return new ResponseEntity<MyErrorDetail>(myError, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetail> validationExceptionHandler(MethodArgumentNotValidException manve){
		MyErrorDetail myError= new MyErrorDetail();
		myError.setTimeStamp(LocalDateTime.now());
		myError.setMessage(manve.getBindingResult().getFieldError().getDefaultMessage());
		myError.setDetails("Validation Error...");
		
		return new ResponseEntity<MyErrorDetail>(myError, HttpStatus.BAD_GATEWAY);
	}
	
}
