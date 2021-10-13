package com.employee.example.exception;

/**
 * @author Aarti
 *
 */

public class EmptyInputException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;


	private String errorMessage;
	
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public EmptyInputException(String errorMessage) {
		super();
		
		this.errorMessage = errorMessage;
	}
	public EmptyInputException() {
		
	}
	
}
