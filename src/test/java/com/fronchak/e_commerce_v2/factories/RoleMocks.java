package com.fronchak.e_commerce_v2.factories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fronchak.e_commerce_v2.dtos.role.RoleOutputDTO;
import com.fronchak.e_commerce_v2.entities.Role;

public class RoleMocks {

	public static Role mockRole() {
		return mockRole(0);
	}
	
	public static Role mockRole(int i) {
		Role mock = new Role();
		mock.setId(mockId(i));
		mock.setAuthority(mockAuthority(i));
		return mock;
	}
	
	private static Long mockId(int i) {
		return i + 30L;
	}
	
	private static String mockAuthority(int i) {
		return "Mock role " + i;
	}
	
	public static RoleOutputDTO mockRoleOutputDTO() {
		return mockRoleOutputDTO(0);
	}
	
	public static RoleOutputDTO mockRoleOutputDTO(int i) {
		RoleOutputDTO mock = new RoleOutputDTO();
		mock.setId(mockId(i));
		mock.setAuthority(mockAuthority(i));
		return mock;
	}
	
	public static Set<Role> mockRoles() {
		Set<Role> mocks = new HashSet<>();
		mocks.add(mockRole(0));
		mocks.add(mockRole(1));
		return mocks;
	}
	
	public static List<RoleOutputDTO> mockRoleOutputDTOs() {
		List<RoleOutputDTO> mocks = new ArrayList<>();
		mocks.add(mockRoleOutputDTO(0));
		mocks.add(mockRoleOutputDTO(1));
		return mocks;
	}
}
