package com.fairy.models.dto.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "fairy_base_menu" )
public @Data class FairyBaseMenu {
	@Id
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "icon")
	private String icon;
	@Column(name = "path")
	private String path;
	@Column(name = "parent_id")
	private Long parentId;
	@Column(name = "compositor")
	private Integer compositor;
}
