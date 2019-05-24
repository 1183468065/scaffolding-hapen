package com.example.exception;

import java.io.Serializable;

public class UnlegalParamException extends RuntimeException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnlegalParamException(Exception e) {
		super(e);
	}

	public UnlegalParamException(String message) {
		super(message);
	}
}
