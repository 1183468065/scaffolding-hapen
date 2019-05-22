package com.example.result;

import com.example.utils.JsonUtil;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

public class Result<T> extends ResultResponse {

	private static final long serialVersionUID = 1L;

	private T data = null;

	/**
	 * Only used when 202 Created
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String location;

	public Result() {
	}

	public Result(ResultCode code) {
		this(code, null, null);
	}

	public Result(ResultCode code, String message, T data) {
		super(code, message);
		this.data = data;
	}

	public Result(ResultCode code, T data) {
		super(code, null);
		this.data = data;
	}

	public Result<T> setResultCode(ResultCode code) {
		super.setResultCode(code);

		return this;
	}

	public Result<T> copyResultCode(ResultResponse result) {
		super.copyResultCode(result);
		return this;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public T listData() {
		if (data == null) {
			return (T) new ArrayList();
		}

		return data;
	}

	public T getData() {
		return data;
	}

	public Result<T> setData(T data) {
		this.data = data;

		return this;
	}

	@Override
	public String toString() {
		return JsonUtil.toJson(this);
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}