package com.fronchak.e_commerce_v2util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.fronchak.e_commerce_v2.dtos.user.UserOutputDTO;

public class UserAsserts {

	public static void assertUserOutputDTO_0(UserOutputDTO result) {
		assertEquals(40L, result.getId());
		assertEquals("Mock user username 0", result.getUsername());
	}
	
	public static void assertUserOutputDTO_1(UserOutputDTO result) {
		assertEquals(41L, result.getId());
		assertEquals("Mock user username 1", result.getUsername());
	}
	
	public static void assertUserOutputDTOs(List<UserOutputDTO> result) {
		assertUserOutputDTO_0(result.get(0));
		assertUserOutputDTO_1(result.get(1));
	}
}
