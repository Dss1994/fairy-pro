package com.fairy.config.filters.imp;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.fairy.config.filters.interfaces.FairyFilter;
import com.fairy.models.common.Session;
import com.fairy.models.dto.RequestDto;
import com.fairy.models.dto.jpa.FairyBaseRole;
import com.fairy.models.dto.jpa.FairyBaseRoute;
import com.fairy.models.dto.jpa.FairyGrantRoute;
import com.fairy.models.logic.jpa.RouteGrantModelJpa;
import com.fairy.models.logic.jpa.RouteModelJpa;

/**
 *  数据库维护当前角色可以访问的URI路径,如果为维护则无法访问
 */
@Component
public class RouteFilter implements FairyFilter{
	@Autowired
	private Session session;
	@Autowired
	private RouteModelJpa routeModelJpa;
	@Autowired
	private RouteGrantModelJpa routeGrantModelJpa;
	@Override
	public Result isAllow(RequestDto<JSONObject> requestDto, HttpServletRequest request) {
		String requestUrl = request.getRequestURI().trim();
		Optional<FairyBaseRoute> route = routeModelJpa.findRouteByTarget(requestUrl);
		if(route.isPresent()) {
			Integer routeType = route.get().getRouteType();
			if(routeType == 4) {
				return Result.getSuccess(request);
			}else {
				Optional<FairyBaseRole> role = session.getCurrentRole(requestDto);
				Optional<FairyGrantRoute> grantRole = routeGrantModelJpa.findGrantRoleByRouteId(
						route.get().getId(),role.get().getId());
			    if(grantRole.isPresent()) {
			    	return Result.getSuccess(request);
			    }else {
			    	return Result.getError(request, String.format("Unauthorized access [ %s ]", requestUrl));
			    }
			}
		}else {
			return Result.getError(request,String.format("Path not maintained [ %s ]", requestUrl));
		}
	}

}
