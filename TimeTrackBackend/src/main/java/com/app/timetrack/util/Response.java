package com.app.timetrack.util;

import org.springframework.http.HttpStatus;

public class Response<T> {
	private HttpStatus httpStatusCode;
	private String  message;
	private T data;
	
	public HttpStatus getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(HttpStatus httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Response(HttpStatus httpStatusCode, String message, T data) {
		super();
		this.httpStatusCode = httpStatusCode;
		this.message = message;
		this.data = data;
	}


}