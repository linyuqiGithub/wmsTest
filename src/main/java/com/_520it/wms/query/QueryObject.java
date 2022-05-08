package com._520it.wms.query;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter@Getter@ToString
public class QueryObject {
	private Integer currentPage = 1;
	private Integer pageSize = 10;
    
    public Integer getStart(){
    	if(currentPage != null && pageSize != null) {
			return (currentPage - 1) * pageSize;
		}
		return null;
    }
}
