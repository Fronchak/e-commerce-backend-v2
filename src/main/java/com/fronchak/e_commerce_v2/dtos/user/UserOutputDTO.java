package com.fronchak.e_commerce_v2.dtos.user;

import java.io.Serializable;

import com.fronchak.e_commerce_v2.entities.User;

public class UserOutputDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String username;
	
	public UserOutputDTO() {}
	
	public UserOutputDTO(User entity) {
		id = entity.getId();
		username = entity.getUsername();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
