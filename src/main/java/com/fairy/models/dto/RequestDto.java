package com.fairy.models.dto;

import java.io.Serializable;

import com.fairy.models.session.Session;

public class RequestDto<T> implements Serializable, Session {
	private static final long serialVersionUID = 3588343400070835840L;
	
	// ��ϢΨһID
	private String id;
	
	// �û����͵�����
	private String token;
	
	// �û������������Ϣ
	private T body;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
	
}
