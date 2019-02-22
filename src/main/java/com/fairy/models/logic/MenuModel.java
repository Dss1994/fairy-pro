package com.fairy.models.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairy.models.logic.jpa.BaseMenuModelJpa;
import com.google.common.collect.Lists;

import lombok.Data;

@Service
public class MenuModel {
	public @Data class Menu{
		private String name;
		private String icon;
		private List<Menu> routes= Lists.newArrayList();
	}
	
	@Autowired
	private BaseMenuModelJpa baseMenuModelJpa;
	
	/**
	 * 根据父节点和角色,查询出来当前所有符合的菜单
	 * @param parentId 父节点
	 * @param roleId   角色id
	 * @return
	 */
	public List<Menu> findAccessibleMenuAll(Long parentId,Long roleId){
		List<Menu> result = Lists.newArrayList();
		baseMenuModelJpa.findAccessibleMenu(parentId, roleId).forEach((data)->{
			Menu menu = new Menu();
			List<Menu> children = findAccessibleMenuAll(data.getId(),roleId);
			menu.setIcon(data.getIcon());
			menu.setName(data.getName());
			menu.setRoutes(children);
			result.add(menu);
		});
		return result;
	}
}
