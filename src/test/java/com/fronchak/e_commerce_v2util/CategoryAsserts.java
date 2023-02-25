package com.fronchak.e_commerce_v2util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.fronchak.e_commerce_v2.dtos.category.CategoryNameOutputDTO;
import com.fronchak.e_commerce_v2.dtos.category.CategoryOutputDTO;
import com.fronchak.e_commerce_v2.entities.Category;

public class CategoryAsserts {

	public static void assertCategoryOutputDTO(CategoryOutputDTO result) {
		assertEquals(10L, result.getId());
		assertEquals("Mock category name 0", result.getName());
		assertEquals("Mock category description 0", result.getDescription());
		assertEquals("http://category0.jpg", result.getImgUrl());
	}

	public static void assertCategoryNameOutputDTO_0(CategoryNameOutputDTO result) {
		assertEquals(10L, result.getId());
		assertEquals("Mock category name 0", result.getName());
		assertEquals("http://category0.jpg", result.getImgUrl());
	}
	
	public static void assertCategoryNameOutputDTO_1(CategoryNameOutputDTO result) {
		assertEquals(11L, result.getId());
		assertEquals("Mock category name 1", result.getName());
		assertEquals("http://category1.jpg", result.getImgUrl());
	}
	
	public static void assertCategoryNameOutputDTOs(List<CategoryNameOutputDTO> results) {
		assertCategoryNameOutputDTO_0(results.get(0));
		assertCategoryNameOutputDTO_1(results.get(1));
	}
	
	public static void assertCategoryProperties(Category result) {
		assertEquals("Mock category name 0", result.getName());
		assertEquals("Mock category description 0", result.getDescription());
		assertEquals("http://category0.jpg", result.getImgUrl());
	}
}
