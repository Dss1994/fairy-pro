package com.fairy.config.filters.imp;

import javax.servlet.annotation.WebFilter;

import org.springframework.context.ApplicationContext;

import com.fairy.FairyProApplication;
import com.fairy.config.filters.abstracts.GlobalRouteFilter;

@WebFilter(filterName = "GlobalFilter", urlPatterns = "/*")
public class GlobalRouteFilterImp extends GlobalRouteFilter{
	@Override
	protected ApplicationContext getApplicationContext() {
		return FairyProApplication.getSpringContext();
	}

}
