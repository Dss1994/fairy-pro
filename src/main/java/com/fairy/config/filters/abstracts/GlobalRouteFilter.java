package com.fairy.config.filters.abstracts;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fairy.config.filters.interfaces.FairyFilter;
import com.fairy.config.filters.interfaces.FairyFilter.Result;
import com.fairy.models.common.RequestWrapper;
import com.fairy.models.dto.RequestDto;
import com.fairy.models.dto.ResponseDto;
import com.google.common.base.Charsets;
/**
 * 	路由拦截器,基于Spring 实现的拦截,通过重写getApplicationContext,以及实现FairyFilter来注册拦截器
 */
abstract public class GlobalRouteFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		RequestWrapper requestWrapper = new RequestWrapper((HttpServletRequest)request);
		RequestDto<JSONObject> requestDto = JSON.parseObject(requestWrapper.toString(),new TypeReference<RequestDto<JSONObject>>() {});
		
		Map<String, FairyFilter> filters = getApplicationContext().getBeansOfType(FairyFilter.class);
		
		// 开始调用拦截器内容
		for(String k : filters.keySet()) {
			try {
				Result result = filters.get(k).isAllow(requestDto, (HttpServletRequest)request);
				if(result.isStatus() == false) {
					throw new Exception(result.getMessage());
				}
			} catch (Exception e) {
				e.printStackTrace();
				ResponseDto<String> resp = new ResponseDto<String>();
				resp.setData(e.getMessage());
				resp.setStatus(510);
				response.getOutputStream().write(JSON.toJSONString(resp).getBytes(Charsets.UTF_8));
				return ;
			}
			
		}
		// 结束
		
		// 放行到下一个拦截器
		chain.doFilter(requestWrapper, response);
		return;
	}
	
	
	/**
	 *  获取Spring的上下文
	 * @return
	 */
	abstract protected ApplicationContext getApplicationContext();
}
