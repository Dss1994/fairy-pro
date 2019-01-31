package com.fairy.models;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fairy.models.dto.RequestDto;
import com.fairy.models.dto.ResponseDto;
import com.fairy.models.dto.jpa.FairyBaseSession;
import com.fairy.models.dto.jpa.FairyBaseUser;
import com.fairy.models.dto.jpa.FairyGrantRole;
import com.fairy.models.logic.UserModel;
import com.fairy.models.logic.UserModel.RespSession;
import com.fairy.models.logic.UserModel.UserVerifyStatus;
import com.fairy.models.logic.jpa.RoleGroupModelJpa;
import com.fairy.models.logic.jpa.SessionModelJpa;
import com.fairy.models.logic.jpa.UserModelJpa;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserModel {
   @Autowired
   private UserModel userModel;
   @Autowired
   private UserModelJpa userModelJpa;
   @Autowired
   private SessionModelJpa sessionModelJpa;
   @Autowired
   private RoleGroupModelJpa roleGroupModelJpa;
   @Autowired
   private WebApplicationContext webApplicationContext;
   private MockMvc mockMvc;
   @Before      
   public void setUp(){      
	   mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
               .build();
   }     
   
   @Test
   public void testIsHaveUser() {
	   String loginName = "notHaveUser";
	   List<FairyBaseUser> users =  userModelJpa.findUserByLoginName(loginName);
	   UserVerifyStatus result = userModel.verify(users,loginName,"1234");
	   assertEquals(result, UserVerifyStatus.USER_DOES_NOT_EXIST);
	   System.out.println(result);
   }
   
   @Test
   public void testLogin() {
	   int num = sessionModelJpa.findAll().size();
	   RespSession map =  userModel.login("admin", "admin", "127.0.0.1", 0);
	   assertEquals(UserVerifyStatus.SUCCESS,map.getStatus());
	   assertEquals(num+1,sessionModelJpa.findAll().size());
	   System.out.println(JSON.toJSONString(map));
	   FairyBaseSession fbs = sessionModelJpa.findBySessionCode(map.getSessionCode()).get();
	   sessionModelJpa.delete(fbs);
   }
   
   
   @Test
   public void testOLogot() {
	   sessionModelJpa.findAll().forEach((data)->{
		   if(!"5e7187526bb84317913fdb781cce04ae323debabfe84469ebb873af1cc18113e".equals(data.getSessionCode())) {
			   userModel.logout(data.getSessionCode());
		   }
	   });
	   assertEquals(sessionModelJpa.findAll().size(), 1);
   }
   
   @Test  
   public void testOLogotController() throws UnsupportedEncodingException, Exception {
	   RequestDto<JSONObject> request = new  RequestDto<JSONObject>();
	   JSONObject json = new JSONObject();
	   json.put("loginName", "admin");
	   json.put("password", "admin");
	   json.put("equipment", 0);
	   request.setData(json);
	   
	   String text = mockMvc.perform(post("/api/user/login").
			   contentType(MediaType.APPLICATION_JSON_UTF8).
			   content(JSON.toJSONString(request))) .andReturn().getResponse().getContentAsString(); 
	   
	   ResponseDto<RespSession> resp = JSON.parseObject(text,new TypeReference<ResponseDto<RespSession>>() {});
	   
	   
	   RequestDto<JSONObject> req = new  RequestDto<JSONObject>();
	   req.setToken(resp.getData().getSessionCode());
	   
	   String jtext = mockMvc.perform(post("/api/user/logout").
			   contentType(MediaType.APPLICATION_JSON_UTF8).
			   content(JSON.toJSONString(req))) .andReturn().getResponse().getContentAsString(); 
	   
	   ResponseDto<String> rs = JSON.parseObject(jtext,new TypeReference<ResponseDto<String>>() {});
	   assertEquals(200,(int)rs.getStatus());
   }
   
   @Test
   @Transactional
   public void testLoginController() throws UnsupportedEncodingException, Exception {
	   RequestDto<JSONObject> request = new  RequestDto<JSONObject>();
	   JSONObject json = new JSONObject();
	   json.put("loginName", "admin");
	   json.put("password", "admin");
	   json.put("equipment", 0);
	   request.setData(json);
	   
	   String text = mockMvc.perform(post("/api/user/login").
			   contentType(MediaType.APPLICATION_JSON_UTF8).
			   content(JSON.toJSONString(request))) .andReturn().getResponse().getContentAsString(); 
	   
	   ResponseDto<RespSession> resp = JSON.parseObject(text,new TypeReference<ResponseDto<RespSession>>() {});
	   assertEquals(200,(int)resp.getStatus());
	   sessionModelJpa.deleteBySessionCode(resp.getData().getSessionCode());
	   
   }
   
   @Test
   public void testAddUserController() throws Exception {
	   RequestDto<JSONObject> request = new RequestDto<JSONObject>();
	   
	   JSONObject json = new JSONObject();
	   json.put( "loginName","zhangj");
	   json.put( "realName","张尽");
	   json.put( "identityCard","429005199609080071");
	   json.put( "password","admin");
	   json.put( "roleId",1);
	   json.put( "email", "zhangjin0908@Hotmail.com");
	   
	   request.setData(json);
	   request.setToken("5e7187526bb84317913fdb781cce04ae323debabfe84469ebb873af1cc18113e");
	   
	   String text = mockMvc.perform(post("/api/user/addUser").
			   contentType(MediaType.APPLICATION_JSON_UTF8).
			   content(JSON.toJSONString(request))) .andReturn().getResponse().getContentAsString(); 
	   ResponseDto<String> resp = JSON.parseObject(text,new TypeReference<ResponseDto<String>>() {});
	   int status = resp.getStatus();
	   assertEquals(200,status);
	   
	   FairyBaseUser fbu = userModelJpa.findUserByLoginName("zhangj").get(0);
	   
	   FairyGrantRole fgr = roleGroupModelJpa.findByUserId(fbu.getId()).get(0);
       roleGroupModelJpa.delete(fgr);	   
	   userModelJpa.delete(fbu);
	   
   }

}
