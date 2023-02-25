package com.fronchak.e_commerce_v2util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fronchak.e_commerce_v2.dtos.product.ProductOutputDTO;
import com.fronchak.e_commerce_v2.dtos.product.ProductSimpleOutputDTO;

public class ProductAsserts {

	public static void assertProductSimpleOutputDTO_0(ProductSimpleOutputDTO result) {
		assertEquals(20L, result.getId());
		assertEquals("Mock product name 0", result.getName());
		assertEquals("http://product0.jpg", result.getImgUrl());
		assertEquals(1.0, result.getPrice());
	}

	public static void assertProductSimpleOutputDTO_1(ProductSimpleOutputDTO result) {
		assertEquals(21L, result.getId());
		assertEquals("Mock product name 1", result.getName());
		assertEquals("http://product1.jpg", result.getImgUrl());
		assertEquals(2.0, result.getPrice());
	}
	
	public static void assertProductSimpleOutputDTOs(Page<ProductSimpleOutputDTO> result) {
		List<ProductSimpleOutputDTO> list = result.getContent();
		assertProductSimpleOutputDTO_0(list.get(0));
		assertProductSimpleOutputDTO_1(list.get(1));
	}
	
	public static void assertProductOutputDTO(ProductOutputDTO result) {
		assertEquals(20L, result.getId());
		assertEquals("Mock product name 0", result.getName());
		assertEquals("Mock product description 0", result.getDescription());
		assertEquals("http://product0.jpg", result.getImgUrl());
		assertEquals(1.0, result.getPrice());
		assertTrue(result.isInStock());
	}
}
