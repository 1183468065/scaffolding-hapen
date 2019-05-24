package com.example.exception;

import java.io.Serializable;

public class EncryptException extends RuntimeException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EncryptException(Exception e) {
		super(e);
	}

	public EncryptException(String message) {
		super(message);
	}
}
