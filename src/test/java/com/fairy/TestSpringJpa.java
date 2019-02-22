package com.fairy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.fairy.models.common.SnowflakeIdGenerator;
import com.fairy.models.dto.jpa.FairyBaseUser;
import com.fairy.models.logic.jpa.BaseUserModelJpa;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSpringJpa {
	@Autowired
	private BaseUserModelJpa userModelJpa;
	@Test
	public void testQuery() {
		List<FairyBaseUser> query = userModelJpa.findAll();
		int size = query.size();
		assertNotEquals(size, -1);
	}
	@Autowired
	private SnowflakeIdGenerator snowflakeId;
	
	@Test
	public void testQueryUserPage() {
	
		Page<Map<String,Object>> datas = userModelJpa.findUserInfoPage("%0%", PageRequest.of(0, 2));
		System.out.println(JSON.toJSONString(datas));
	}
	@Test
	public void testCreateUser() {
		FairyBaseUser user = new FairyBaseUser();
		user.setCreateTime(new Date());
		user.setEmail("zhangjin0908@hotmail.com");
		user.setIdentityCard("429005199609080071");
		user.setLoginName("test");
		user.setPassword("zhangj");
		user.setRealName("张尽");
		user.setId(snowflakeId.nextId());
		userModelJpa.save(user);
		
		assertEquals(userModelJpa.findUserByLoginName("test").size(),1);
		
		System.out.println(JSON.toJSONString(userModelJpa.findUserByLoginName("test").get(0)));
		userModelJpa.delete(user);
	}
	
}
