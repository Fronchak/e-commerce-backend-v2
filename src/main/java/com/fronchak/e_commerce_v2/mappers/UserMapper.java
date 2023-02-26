package com.fronchak.e_commerce_v2.mappers;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fronchak.e_commerce_v2.dtos.user.UserInputDTO;
import com.fronchak.e_commerce_v2.dtos.user.UserOutputDTO;
import com.fronchak.e_commerce_v2.dtos.user.UserRolesOutputDTO;
import com.fronchak.e_commerce_v2.entities.User;

@Service
public class UserMapper {

	@Autowired
	private RoleMapper roleMapper;
	
	public UserOutputDTO convertUserToUserOutputDTO(User entity) {
		return new UserOutputDTO(entity);
	}
	
	public List<UserOutputDTO> convertUsersToUserOutputDTOs(Collection<User> entities) {
		return entities.stream()
				.map((entity) -> convertUserToUserOutputDTO(entity))
				.collect(Collectors.toList());
	}
	
	public UserRolesOutputDTO convertUserToUserRolesOutputDTO(User entity) {
		UserRolesOutputDTO dto = new UserRolesOutputDTO(entity);
		dto.setRoles(roleMapper.convertRolesToRoleOutputDTOs(entity.getRoles()));
		return dto;
	}
	
	public void copyUserInputDTOToEntity(UserInputDTO dto, User entity) {
		
	}
}
