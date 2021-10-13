package com.employee.example.exception;

/**
 * @author Aarti
 *
 */

public class IdDoesNotExist extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	
	private String errorMessage;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public IdDoesNotExist(String errorMessage) {
		super();
		
		this.errorMessage = errorMessage;
	}
	
	public IdDoesNotExist() {
		
	}
	
}
