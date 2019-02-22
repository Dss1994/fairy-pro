package com.fairy.models.dto.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "fairy_base_session" )
public @Data class FairyBaseSession implements Serializable{
	private static final long serialVersionUID = 647410867485886904L;
	@Id
	@Column(name="id")
	private Long id;
	@Column(name = "user_id")
	private Long userId;
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name="last_time")
	private Date lastTime;
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name="create_time")
	private Date createTime;
	@Column(name="session_code")
	private String sessionCode;
	@Column(name="ip_addr")
	private String ipAddr;
	@Column(name="equipment")
	private Integer equipment;
	public FairyBaseSession(
			Long id,
			Long userId,
			String ipAddr, 
			Integer equipment) {
		super();
		this.ipAddr = ipAddr;
		this.equipment = equipment;
		this.id = id;
		this.userId = userId;
		this.sessionCode = (UUID.randomUUID().toString()+UUID.randomUUID().toString()).replaceAll("-", "");
		this.createTime=new Date();
		this.lastTime = new Date();
	}
	
	public FairyBaseSession() {
	}

}
