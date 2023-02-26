package com.fronchak.e_commerce_v2.factories;

import java.util.ArrayList;
import java.util.List;

import com.fronchak.e_commerce_v2.dtos.user.UserOutputDTO;
import com.fronchak.e_commerce_v2.dtos.user.UserRolesOutputDTO;
import com.fronchak.e_commerce_v2.entities.User;

public class UserMocks {

	public static User mockUser() {
		return mockUser(0);
	}
	
	public static User mockUser(int i) {
		User mock = new User();
		mock.setId(mockId(i));
		mock.setUsername(mockUsername(i));
		mock.setPassword(mockPassword(i));
		mock.addRole(RoleMocks.mockRole(0));
		mock.addRole(RoleMocks.mockRole(1));
		return mock;
	}
	
	private static Long mockId(int i) {
		return i + 40L;
	}
	
	private static String mockUsername(int i) {
		return "Mock user username " + i;
	}
	
	private static String mockPassword(int i) {
		return "Mock user password " + i;
	}
	
	public static UserOutputDTO mockUserOutputDTO() {
		return mockUserOutputDTO(0);
	}
	
	public static UserOutputDTO mockUserOutputDTO(int i) {
		UserOutputDTO mock = new UserOutputDTO();
		mock.setId(mockId(i));
		mock.setUsername(mockUsername(i));
		return mock;
	}
	
	public static UserRolesOutputDTO mockUserRoleOutputDTO() {
		UserRolesOutputDTO mock = new UserRolesOutputDTO();
		mock.setId(mockId(0));
		mock.setUsername(mockUsername(0));
		mock.setRoles(RoleMocks.mockRoleOutputDTOs());
		return mock;
	}
	
	public static List<User> mockUsers() {
		List<User> mocks = new ArrayList<>();
		mocks.add(mockUser(0));
		mocks.add(mockUser(1));
		return mocks;
	}
	
	public static List<UserOutputDTO> mockUserOutputDTOs() {
		List<UserOutputDTO> mocks = new ArrayList<>();
		mocks.add(mockUserOutputDTO(0));
		mocks.add(mockUserOutputDTO(1));
		return mocks;
	}
}
