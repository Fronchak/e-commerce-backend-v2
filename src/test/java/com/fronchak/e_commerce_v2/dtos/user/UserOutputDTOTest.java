package com.fronchak.e_commerce_v2.dtos.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.e_commerce_v2.entities.User;
import com.fronchak.e_commerce_v2.factories.UserMocks;
import com.fronchak.e_commerce_v2util.UserAsserts;

@ExtendWith(SpringExtension.class)
public class UserOutputDTOTest {

	@Test
	public void constructorWithUserShouldCreateCorrectlyObject() {
		User entity = UserMocks.mockUser();
		
		UserOutputDTO result = new UserOutputDTO(entity);
		
		UserAsserts.assertUserOutputDTO_0(result);
	}
}
