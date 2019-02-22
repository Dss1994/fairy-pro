package com.fairy.models.dto.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "fairy_base_route" )
public @Data class FairyBaseRoute {
	@Id
	@Column(name = "id")
	private Long id;
	@Column(name = "target")
	private String target;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "status")
	private Integer status;
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name = "createTime")
	private Date createTime;
	@Column(name = "route_type")
	private Integer routeType;
}
