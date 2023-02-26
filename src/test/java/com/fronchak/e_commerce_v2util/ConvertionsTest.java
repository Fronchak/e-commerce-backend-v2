package com.fronchak.e_commerce_v2util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.e_commerce_v2.entities.enums.Grade;
import com.fronchak.e_commerce_v2.util.Convertions;

@ExtendWith(SpringExtension.class)
public class ConvertionsTest {

	@Test
	public void getGradeShouldReturnExcelentWhenAverageIsGreaterThanFourDotFive() {
		Grade result  = Convertions.getGrade(4.5);
		assertEquals(Grade.EXCELENT, result);
	}
	
	@Test
	public void getGradeShouldReturnGoodWhenAverageIsGreaterThanThreeDotFive() {
		Grade result  = Convertions.getGrade(3.5);
		assertEquals(Grade.GOOD, result);
	}
	
	@Test
	public void getGradeShouldReturnHorribleWhenAverageIsLoweThanOneDotFive() {
		Grade result  = Convertions.getGrade(1.49);
		assertEquals(Grade.HORRIBLE, result);
	}
}
