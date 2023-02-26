package com.fronchak.e_commerce_v2.dtos.user;

import java.util.ArrayList;
import java.util.List;

import com.fronchak.e_commerce_v2.dtos.role.RoleOutputDTO;
import com.fronchak.e_commerce_v2.entities.User;

public class UserRolesOutputDTO extends UserOutputDTO {

	private static final long serialVersionUID = 1L;
	
	public UserRolesOutputDTO() {
		super();
	}
	
	public UserRolesOutputDTO(User entity) {
		super(entity);
	}
	
	private List<RoleOutputDTO> roles = new ArrayList<>();

	public List<RoleOutputDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleOutputDTO> roles) {
		this.roles = roles;
	}
}
