package com.fairy.models.common;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.fairy.models.dto.RequestDto;
import com.fairy.models.dto.jpa.FairyBaseRole;
import com.fairy.models.dto.jpa.FairyBaseSession;
import com.fairy.models.dto.jpa.FairyBaseUser;
import com.fairy.models.logic.jpa.RoleGroupModelJpa;
import com.fairy.models.logic.jpa.RoleModelJpa;
import com.fairy.models.logic.jpa.SessionModelJpa;
import com.fairy.models.logic.jpa.UserModelJpa;

@Component
public class Session {
	@Autowired
	private SessionModelJpa sessionModelJpa;
	@Autowired
	private RoleGroupModelJpa roleGroupModelJpa;
	@Autowired
	private RoleModelJpa roleModelJpa;
	@Autowired
	private UserModelJpa userModelJpa;
	
	public Optional<FairyBaseUser> getCurrentUser(RequestDto<?> request){
		return getCurrentUser(request.getToken());
	}
	
	public Optional<FairyBaseUser> getCurrentUser(String token) {
		FairyBaseSession queryValue = new FairyBaseSession();
		queryValue.setSessionCode(token);
		
		Example<FairyBaseSession> example = Example.of(queryValue);
		example.getMatcher().withMatcher("sessionCode", match ->  match.exact());
		
		
		Long userId = sessionModelJpa.findOne(example).get().getUserId();
		FairyBaseUser queryUser= new FairyBaseUser();
		queryUser.setId(userId);
		
		Example<FairyBaseUser> userexample = Example.of(queryUser);
		example.getMatcher().withMatcher("id", match ->  match.exact());
		return userModelJpa.findOne(userexample);
	}
	
	public Optional<FairyBaseRole> getCurrentRole(RequestDto<?> request){
		return getCurrentRole(request.getToken());
	}
	
	public Optional<FairyBaseRole> getCurrentRole(String token){
		Long userId = getCurrentUser(token).get().getId();
		FairyBaseRole roleQuery = new FairyBaseRole();
		Long roeId = roleGroupModelJpa.findByUserId(userId).get(0).getRoleId();
		roleQuery.setId(roeId);
		Example<FairyBaseRole> example = Example.of(roleQuery);
		example.getMatcher().withMatcher("id",  match ->  match.exact());
		return roleModelJpa.findOne(example);
	}
}
