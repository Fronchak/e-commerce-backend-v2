package com.fronchak.e_commerce_v2.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.e_commerce_v2.dtos.role.RoleOutputDTO;
import com.fronchak.e_commerce_v2.entities.Role;
import com.fronchak.e_commerce_v2.factories.RoleMocks;
import com.fronchak.e_commerce_v2.mappers.RoleMapper;
import com.fronchak.e_commerce_v2.repositories.RoleRepository;

@ExtendWith(SpringExtension.class)
public class RoleServiceTest {

	@Mock
	private RoleRepository repository;
	
	@Mock
	private RoleMapper mapper;
	
	@InjectMocks
	private RoleService service;
	
	@Test
	public void findAllShouldReturnRoleOutputDTOs() {
		List<Role> entities = new ArrayList<>();
		List<RoleOutputDTO> dtos = RoleMocks.mockRoleOutputDTOs();
		
		when(repository.findAll()).thenReturn(entities);
		when(mapper.convertRolesToRoleOutputDTOs(entities)).thenReturn(dtos);
		
		List<RoleOutputDTO> result = service.findAll();
		
		assertSame(dtos, result);
	}
}
