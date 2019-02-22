package com.fairy.controllers.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fairy.models.common.Session;
import com.fairy.models.dto.RequestDto;
import com.fairy.models.dto.ResponseDto;
import com.fairy.models.logic.MenuModel;
import com.fairy.models.logic.MenuModel.Menu;

@RestController
@RequestMapping(value ="/api/menu", method=RequestMethod.POST )
public class MenuController {
	@Autowired private MenuModel menuModel;
	@Autowired private Session session;
	
	@RequestMapping("/getAccessibleMenuAll")
	public ResponseDto<List<Menu>> getAccessibleMenuAll(@RequestBody RequestDto<JSONObject> request){
		return ResponseDto.getSuccess(menuModel.findAccessibleMenuAll(0L, session.getCurrentRole(request).get().getId())) ;
	}
}
