package com.fronchak.e_commerce_v2.mappers;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.e_commerce_v2.dtos.role.RoleOutputDTO;
import com.fronchak.e_commerce_v2.dtos.user.UserOutputDTO;
import com.fronchak.e_commerce_v2.dtos.user.UserRolesOutputDTO;
import com.fronchak.e_commerce_v2.entities.User;
import com.fronchak.e_commerce_v2.factories.RoleMocks;
import com.fronchak.e_commerce_v2.factories.UserMocks;
import com.fronchak.e_commerce_v2util.UserAsserts;

@ExtendWith(SpringExtension.class)
public class UserMapperTest {

	@Mock
	private RoleMapper roleMapper;
	
	@InjectMocks
	private UserMapper userMapper;
	
	@Test
	public void convertUserToUserOutputDTOShouldConvertCorrectly() {
		User entity = UserMocks.mockUser();
		
		UserOutputDTO result = userMapper.convertUserToUserOutputDTO(entity);
		
		UserAsserts.assertUserOutputDTO_0(result);
	}
	
	@Test
	public void convertUsersToUserOutputDTOsShouldConvertCorrectly() {
		List<User> entities = UserMocks.mockUsers();
		
		List<UserOutputDTO> result = userMapper.convertUsersToUserOutputDTOs(entities);
		
		UserAsserts.assertUserOutputDTOs(result);
	}
	
	@Test
	public void convertUserToUserRolesOutputDTOShouldConvertCorrectly() {
		User entity = UserMocks.mockUser();
		List<RoleOutputDTO> roles = RoleMocks.mockRoleOutputDTOs();
		
		when(roleMapper.convertRolesToRoleOutputDTOs(entity.getRoles())).thenReturn(roles);
		
		UserRolesOutputDTO result = userMapper.convertUserToUserRolesOutputDTO(entity);
		
		UserAsserts.assertUserOutputDTO_0(result);
		assertSame(roles, result.getRoles());
	}
}
