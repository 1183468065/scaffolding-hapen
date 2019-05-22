package com.example.criteria;

import com.example.model.BasePojo;
import com.example.pagination.PageInfo;

/**
 * 
 * 
 */
public class PageCriteria extends BasePojo {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int offset = 0;

	private int limit = 10;

	public PageCriteria() {

	}

	public PageCriteria(int offset, int limit) {
		this.offset = offset;
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public PageInfo toPageInfo() {
		return new PageInfo(offset, limit);
	}

	public int getPageNum() {
		if (getLimit() == 0)
			return 0;
		return getOffset() / getLimit() + 1;
	}

	public void setPageNum(int pageNum) {
		setOffset((pageNum - 1) * getLimit());
	}
}
