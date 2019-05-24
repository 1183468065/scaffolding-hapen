package com.example.exception;


import com.example.result.ResultCode;

public class NoAuthorityException extends BaseException {
	
	private static final String DEFAULT_MESSAGE = "没有相关权限";
	
	private static final int exceptionCode = ResultCode.RESULT_NO_AUTHORITY.code();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public NoAuthorityException(String message) {
		super(exceptionCode, DEFAULT_MESSAGE + message);
	}

	public NoAuthorityException() {
		super(exceptionCode, DEFAULT_MESSAGE);
	}
}
