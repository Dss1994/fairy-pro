package com.fairy.config.filters.interfaces;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.fairy.models.dto.RequestDto;

import lombok.Data;

public interface FairyFilter {
	public @Data class Result{
		// 当前拦截器的状态 true表示允许,false表示禁止
		private boolean status;
		// 当前返回的消息,如果失败后会返回对应的错误的消息
		private String message;
		// 当前请求的路径
		private String path;
		
		public static Result getSuccess(HttpServletRequest request,String message) {
			Result result = new Result();
			result.setStatus(true);
			result.setPath(request.getRequestURI());
			result.setMessage(message);
			return result;
		}
		public static Result getSuccess(HttpServletRequest request) {
			Result result = new Result();
			result.setStatus(true);
			result.setPath(request.getRequestURI());
			return result;
		}
		public static Result getError(HttpServletRequest request,String message) {
			Result result = new Result();
			result.setStatus(false);
			result.setPath(request.getRequestURI());
			result.setMessage(message);
			return result;
		}
		public static Result getError(HttpServletRequest request) {
			Result result = new Result();
			result.setStatus(false);
			result.setPath(request.getRequestURI());
			return result;
		}
	}
	/**
	 * 是否允许通过
	 * @param requestDto  请求的dto对象
	 * @param request     请求servlet,注意,这个只能读取数据,不能用于设置
	 * @return  如果返回为true表示允许,false表示禁止
	 */
	Result isAllow(RequestDto<JSONObject> requestDto ,HttpServletRequest request);
}
