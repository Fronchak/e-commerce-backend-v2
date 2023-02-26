package com.fronchak.e_commerce_v2.dtos.role;

import java.io.Serializable;

import com.fronchak.e_commerce_v2.entities.Role;

public class RoleOutputDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String authority;
	
	public RoleOutputDTO() {}
	
	public RoleOutputDTO(Role entity) {
		id = entity.getId();
		authority = entity.getAuthority();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
