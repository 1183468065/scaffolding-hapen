package com.example.exception;

import java.io.Serializable;

public class ErrorInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer code;
	
	private String url;
	
	private String message;
	
	private String errorPage;
	
	public ErrorInfo() {
		super();
	}
	
	public ErrorInfo(Integer code, String message, String url, String errorPage) {
		this.code = code;
		this.message = message;
		this.url = url;
		this.errorPage = errorPage;
	}
	
	public ErrorInfo(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public ErrorInfo(Integer code, String message, String url) {
		this.code = code;
		this.message = message;
		this.url = url;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getErrorPage() {
		return errorPage;
	}
	
	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}
	
}
