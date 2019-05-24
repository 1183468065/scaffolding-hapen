package com.example.exception;

import java.io.Serializable;

public class ServiceException extends RuntimeException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ErrorInfo errorInfo;
	
	public ServiceException(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}
	
	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}
	
	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}
}
