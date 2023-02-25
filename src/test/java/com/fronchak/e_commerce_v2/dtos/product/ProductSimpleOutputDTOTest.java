package com.fronchak.e_commerce_v2.dtos.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.e_commerce_v2.entities.Product;
import com.fronchak.e_commerce_v2.factories.ProductMocks;
import com.fronchak.e_commerce_v2util.ProductAsserts;

@ExtendWith(SpringExtension.class)
public class ProductSimpleOutputDTOTest {

	@Test
	public void constructorWithProductShouldCreateCorrectlyObject() {
		Product entity = ProductMocks.mockProduct();
		
		ProductSimpleOutputDTO result = new ProductSimpleOutputDTO(entity);
		
		ProductAsserts.assertProductSimpleOutputDTO_0(result);
	}
}
