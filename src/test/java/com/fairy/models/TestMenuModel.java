package com.fairy.models;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.fairy.models.logic.MenuModel;
import com.fairy.models.logic.MenuModel.Menu;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMenuModel {
	@Autowired private MenuModel menuModel;
	@Test
	public void testFindAccessibleMenuAll() {
		List<Menu> datas = menuModel.findAccessibleMenuAll(0L, 0L);
		System.out.println(JSON.toJSONString(datas));
	}
}
