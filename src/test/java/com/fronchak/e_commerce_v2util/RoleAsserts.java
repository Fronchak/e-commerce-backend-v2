package com.fronchak.e_commerce_v2util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.fronchak.e_commerce_v2.dtos.role.RoleOutputDTO;

public class RoleAsserts {

	public static void assertRoleOutputDTO_0(RoleOutputDTO result) {
		assertEquals(30L, result.getId());
		assertEquals("Mock role 0", result.getAuthority());
	}
	
	public static void assertRoleOutputDTO_1(RoleOutputDTO result) {
		assertEquals(31L, result.getId());
		assertEquals("Mock role 1", result.getAuthority());
	}
	
	public static void assertRoleOutputDTOs(List<RoleOutputDTO> result) {
		assertRoleOutputDTO_0(result.get(0));
		assertRoleOutputDTO_1(result.get(1));
	}
}
