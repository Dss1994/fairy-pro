package com.fairy.models.dto.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "fairy_base_user" )
public @Data class FairyBaseUser implements Serializable{
	private static final long serialVersionUID = -6733867825937796722L;
	@Id
	@Column(name = "id")
	private Long id;
	@Column(name = "login_name")
	private String loginName;
	@Column(name = "real_name")
	private String realName;
	@Column(name = "identity_card")
	private String identityCard;
	@Column(name = "password")
	private String password;
	@Column(name = "email")
	private String email;
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name = "create_time")
	private Date createTime;
	@Column(name="online_time")
	private Integer onlineTime = 30;
}
