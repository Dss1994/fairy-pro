package com.fairy.controllers.user;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fairy.models.common.Session;
import com.fairy.models.dto.RequestDto;
import com.fairy.models.dto.ResponseDto;
import com.fairy.models.logic.UserModel;
import com.fairy.models.logic.UserModel.RespSession;

@RestController
@RequestMapping(value ="/api/user", method=RequestMethod.POST )
public class UserController {
	
	@Autowired private UserModel userModel;
	
	@Autowired private Session session;
	
	@RequestMapping("/login")
	public ResponseDto<RespSession> login(@RequestBody RequestDto<JSONObject> request,HttpServletRequest req) {
		JSONObject datas = request.getData();
		String loginName = datas.getString("loginName");
		String password = datas.getString("password");
		Integer equipment = datas.getInteger("equipment");
		return ResponseDto.getSuccess(
				userModel.login(loginName, password, req.getRemoteAddr(), equipment)
		);
	}
	
	@RequestMapping("/logout")
	public ResponseDto<String> logout(@RequestBody RequestDto<JSONObject> request) {
		String token = request.getToken();
		userModel.logout(token);
		return ResponseDto.getSuccess();
	}
	
	@RequestMapping("/delUser")
	public  ResponseDto<String> delUser(@RequestBody RequestDto<JSONObject> request){
		Long userId = request.getData().getLong("userId");
		Integer currentType = session.getCurrentRole(request).get().getRoleType();
		userModel.delUser(userId, currentType);
		return ResponseDto.getSuccess();
	}
	
	@RequestMapping("/addUser")
	public ResponseDto<String> addUser(@RequestBody RequestDto<JSONObject> request) {
		String loginName = request.getData().getString("loginName");
		String realName = request.getData().getString("realName");
		String identityCard = request.getData().getString("identityCard");
		String password = request.getData().getString("password");
		String email = request.getData().getString("email");
		Long roleId = request.getData().getLong("roleId");
		Long currentUser = session.getCurrentUser(request).get().getId();
		userModel.addUser(loginName, realName, identityCard, password, email, currentUser, roleId);
		return ResponseDto.getSuccess();
	}
}
