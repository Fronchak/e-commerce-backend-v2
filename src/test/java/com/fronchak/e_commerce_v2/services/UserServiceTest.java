package com.fronchak.e_commerce_v2.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.e_commerce_v2.dtos.user.UserInsertDTO;
import com.fronchak.e_commerce_v2.dtos.user.UserOutputDTO;
import com.fronchak.e_commerce_v2.entities.Role;
import com.fronchak.e_commerce_v2.entities.User;
import com.fronchak.e_commerce_v2.factories.RoleMocks;
import com.fronchak.e_commerce_v2.factories.UserMocks;
import com.fronchak.e_commerce_v2.mappers.UserMapper;
import com.fronchak.e_commerce_v2.repositories.RoleRepository;
import com.fronchak.e_commerce_v2.repositories.UserRepository;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

	private static final Long VALID_ID = 1L;
	private static final Long INVALID_ID = 2L;
	private static final Long DEPENDENT_ID = 3L;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private RoleRepository roleRepository;
	
	@Mock
	private UserMapper mapper;
	
	@Mock
	private BCryptPasswordEncoder passwordEncoder;
	
	@InjectMocks
	private UserService service;
	
	private User entity;
	private UserOutputDTO outputDTO;
	
	@BeforeEach
	void setUp() {
		entity = UserMocks.mockUser();
		outputDTO = UserMocks.mockUserOutputDTO();
	}
	
	@Test
	public void saveShouldReturnUserOutputDTOAfterSaveEntityWithRoleClient() {
		ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
		UserInsertDTO inputDTO = UserMocks.mockUserInsertDTO();
		Role roleClient = RoleMocks.mockRole(10);
		String encriptedPassword = "abc";
		
		when(passwordEncoder.encode(inputDTO.getPassword())).thenReturn(encriptedPassword);
		when(userRepository.save(any(User.class))).thenReturn(entity);
		when(mapper.convertUserToUserOutputDTO(entity)).thenReturn(outputDTO);
		when(roleRepository.getReferenceById(1L)).thenReturn(roleClient);
		
		UserOutputDTO result = service.save(inputDTO);
		verify(userRepository).save(argumentCaptor.capture());
		User entitySaved = argumentCaptor.getValue();
		
		assertSame(outputDTO, result);
		assertEquals(encriptedPassword, entitySaved.getPassword());
		Set<Role> roles = entitySaved.getRoles();
		assertEquals(1, roles.size());
		assertTrue(roles.contains(roleClient));
	}
}
