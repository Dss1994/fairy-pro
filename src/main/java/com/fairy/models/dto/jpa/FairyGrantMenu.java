package com.fairy.models.dto.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "fairy_grant_menu" )
public @Data class FairyGrantMenu {
	@Id
	@Column(name = "id")
	private Long id;
	@Column(name = "role_id")
	private Long roleId;
	@Column(name = "menu_id")
	private Long menuId;
	@Column(name = "authorize")
	private Long authorize;
	@Column(name = "create_time")
	private Date createTime;
}
