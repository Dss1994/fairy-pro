package com.fairy.controllers.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fairy.models.dto.RequestDto;

/**
  *    ����ģ��,���������û�����,�Լ��û�ע������ģ��
 */
@RestController
@RequestMapping(value ="/api/user", method=RequestMethod.POST )
public class UserController {
	
	/**
	 *  ����ϵͳ
	 * @param request
	 */
	@RequestMapping("/login")
	public void login(RequestDto<JSONObject> request) {
	}
	
	/**
	 * �ǳ�ϵͳ
	 * @param request
	 */
	@RequestMapping("/logout")
	public void logout(RequestDto<JSONObject> request) {
	}
	
	/**
	 * ��ѯ��ǰ�û���Ϣ
	 * @param request
	 */
	@RequestMapping("/queryCurrentUserInfo")
	public void queryCurrentUserInfo(RequestDto<JSONObject> request) {
	}
	
	/**
	 *  �û�ע��
	 * @param request
	 */
	@RequestMapping("/register")
	public void register(RequestDto<JSONObject> request) {
	}
}
