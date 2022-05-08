package com._520it.wms.page;

import java.util.List;

import lombok.Data;
@Data
public class PageResult {
	private Integer currentPage;
	private Integer pageSize;
	private Integer totalCount;
	private List<?> data;
	private Integer totalPage;
	private Integer prevPage;
	private Integer nextPage;

	public PageResult(Integer currentPage, Integer pageSize, Integer totalCount, List<?> data) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.data = data;
		if(currentPage != null && pageSize != null) {
			totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize + 1);
			prevPage = currentPage - 1 > 1 ? currentPage - 1 : 1;
			nextPage = currentPage + 1 > totalPage ? totalPage : currentPage + 1;
		}
	}
	
	
	

}
