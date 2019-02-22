package com.fairy.models.dto;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import lombok.Data;

public @Data class Page<T> {
	public @Data class  Filter{
		private String name;
		private String value;
		private String symbol;
	}
	private Long total;
	private Integer pageSize;
	private Integer pageNo;
	private T data;
	private JSONObject sorts;
	private List<Filter> filters;
	
	/**
	 *   将spring 的page对象转换为可以返回的page对象
	 * @param page
	 * @return
	 */
	static public <T> ResponseDto<Page<List<T>>> toReturnPage(org.springframework.data.domain.Page<T> page){
		Page<List<T>> r = new Page<List<T>>();
		r.setTotal(page.getTotalElements());
		r.setData(page.getContent());
		r.setPageNo(page.getPageable().getPageNumber());
		r.setPageSize(page.getPageable().getPageSize());
		return ResponseDto.getSuccess(r);
	}
}
