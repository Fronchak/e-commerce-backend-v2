package com.fronchak.e_commerce_v2.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ProductTest {

	@Test
	public void productShouldBeCreateWithQuantityEqualsToZero() {
		Product result = new Product();
		
		assertEquals(0, result.getQuantity());
	}
	
	@Test
	public void inStockShouldReturnFalseIfQuantityIsZero() {
		Product result = new Product();
		result.setQuantity(0);
		
		assertFalse(result.inStock());
	}
	
	@Test
	public void inStockShouldReturnTrueIfThereIsAtLeastOneInStock() {
		Product result = new Product();
		result.setQuantity(1);
		
		assertTrue(result.inStock());
	}
}
