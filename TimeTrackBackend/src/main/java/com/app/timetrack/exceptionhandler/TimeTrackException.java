package com.app.timetrack.exceptionhandler;

public class TimeTrackException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	private String errorMessage;
	
	
	public TimeTrackException() {
		super();
	}


	public TimeTrackException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}


	public String getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}


	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
