package com.fronchak.e_commerce_v2util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.fronchak.e_commerce_v2.dtos.brand.BrandOutputDTO;

public class BrandAsserts {

	public static void assertBrandOutputDTO_0(BrandOutputDTO result) {
		assertEquals(0L, result.getId());
		assertEquals("Mock brand name 0", result.getName());
		assertEquals("http://brand0.jpg", result.getImgUrl());
	}
	
	public static void assertBrandOutputDTO_1(BrandOutputDTO result) {
		assertEquals(1L, result.getId());
		assertEquals("Mock brand name 1", result.getName());
		assertEquals("http://brand1.jpg", result.getImgUrl());
	}
	
	public static void assertBrandOutputDTOList(List<BrandOutputDTO> resultList) {
		assertBrandOutputDTO_0(resultList.get(0));
		assertBrandOutputDTO_1(resultList.get(1));
	}
}
