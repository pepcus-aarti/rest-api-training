package com.employee.example.advice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.employee.example.exception.ApiExceptions;
import com.employee.example.exception.EmptyInputException;
import com.employee.example.exception.IdDoesNotExist;

/**
 * @author Aarti
 *
 */

@ControllerAdvice
public class EmployeeControllerAdvice extends ResponseEntityExceptionHandler {
	
		
	/**
	 * It is executed when given id is not present in database.
	 * @param idDoesNotExist
	 * @return
	 */
	@ExceptionHandler(IdDoesNotExist.class)
	public ResponseEntity<Object> handleIdDoesNotExist(IdDoesNotExist idDoesNotExist)
	{
		String message=idDoesNotExist.getErrorMessage();
		List<String> details=new ArrayList<String>();
		details.add("Given Id is not present in database");
		ApiExceptions exception=new ApiExceptions(message,details,HttpStatus.NOT_FOUND,LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
	}
	
	
	/**
	 * It is executed when empty input is given.
	 * @param emptyInputException
	 * @return
	 */
	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<Object> handleEmptyInputException(EmptyInputException emptyInputException)
	{
		String message=emptyInputException.getErrorMessage();
		List<String> details=new ArrayList<String>();
		details.add("Please enter proper name and it should not be blank.");
		ApiExceptions exception=new ApiExceptions(message,details,HttpStatus.NOT_FOUND,LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
	}
	

	/**
	 * It is executed when any constraint is violated.
	 * @param constraintViolationException
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException constraintViolationException)
	{
		String message=constraintViolationException.getMessage();
		List<String> details=new ArrayList<String>();
		details.add("Value should not be null");
		ApiExceptions exception=new ApiExceptions(message,details,HttpStatus.NOT_ACCEPTABLE,LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);  
	}
	

	
	
 @Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		String message=ex.getMethod();
		List<String> details=new ArrayList<String>();
		details.add("Request method is not supported.");
		ApiExceptions exception=new ApiExceptions(message,details,status,LocalDateTime.now());
		return ResponseEntity.status(status).body(exception);
	}

	/**
	 *
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message=ex.getMessage();
		List<String> details=new ArrayList<String>();
		details.add("Media is not supported.");
		ApiExceptions exception=new ApiExceptions(message,details,status,LocalDateTime.now());
		return ResponseEntity.status(status).body(exception);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String message=ex.getMessage();
		List<String> details=new ArrayList<String>();
		details.add("Give number value only and not string.");
		ApiExceptions exception=new ApiExceptions(message,details,status,LocalDateTime.now());
		return ResponseEntity.status(status).body(exception);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		String message=ex.getMessage();
		List<String> details=new ArrayList<String>();
		details.add("Please give proper request body.");
		ApiExceptions exception=new ApiExceptions(message,details,status,LocalDateTime.now());
		return ResponseEntity.status(status).body(exception);
	}

}
