package com.fronchak.e_commerce_v2.mappers;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fronchak.e_commerce_v2.dtos.role.RoleOutputDTO;
import com.fronchak.e_commerce_v2.entities.Role;

@Service
public class RoleMapper {

	public RoleOutputDTO convertRoleToRoleOutputDTO(Role entity) {
		return new RoleOutputDTO(entity);
	}
	
	public List<RoleOutputDTO> convertRolesToRoleOutputDTOs(Collection<Role> entities) {
		return entities.stream()
				.map((entity) -> convertRoleToRoleOutputDTO(entity))
				.collect(Collectors.toList());
	}
}
