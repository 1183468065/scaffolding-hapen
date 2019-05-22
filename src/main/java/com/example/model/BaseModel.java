package com.example.model;

public abstract class BaseModel extends BasePojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long performerId;

	public Long performerId() {
		return performerId;
	}

	public void setPerformerId(Long performerId) {
		this.performerId = performerId;
	}

}
