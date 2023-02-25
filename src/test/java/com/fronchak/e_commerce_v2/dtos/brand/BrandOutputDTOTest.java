package com.fronchak.e_commerce_v2.dtos.brand;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.e_commerce_v2.entities.Brand;
import com.fronchak.e_commerce_v2.factories.BrandMocks;
import com.fronchak.e_commerce_v2util.BrandAsserts;

@ExtendWith(SpringExtension.class)
public class BrandOutputDTOTest {

	@Test
	public void constructorWithBrandShouldCreateCorrectlyObject() {
		Brand entity = BrandMocks.mockBrand();
		
		BrandOutputDTO result = new BrandOutputDTO(entity);
		
		BrandAsserts.assertBrandOutputDTO_0(result);
	}
}
