package com.fronchak.e_commerce_v2.entities.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.e_commerce_v2.exceptions.EnumException;

@ExtendWith(SpringExtension.class)
public class GradeTest {

	@Test
	public void toEnumShouldCreateCorrectlyObject() {
		Grade result = Grade.toEnum(1);
		
		assertEquals(Grade.HORRIBLE, result);
		
		result = Grade.toEnum(5);
		
		assertEquals(Grade.EXCELENT, result);
	}
	
	@Test
	public void toEnumShouldThrowEnumExceptionWhenAvaliationIsNull() {
		assertThrows(EnumException.class, () -> Grade.toEnum(null));
	}
	
	@Test
	public void toEnumShouldThrowEnumExceptionWhenThereIsNoEnumWithTheAvaliationPassed() {
		assertThrows(EnumException.class, () -> Grade.toEnum(10));
	}
}
