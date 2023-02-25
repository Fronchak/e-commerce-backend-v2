package com.fronchak.e_commerce_v2.dtos.category;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.e_commerce_v2.entities.Category;
import com.fronchak.e_commerce_v2.factories.CategoryMocks;
import com.fronchak.e_commerce_v2util.CategoryAsserts;

@ExtendWith(SpringExtension.class)
public class CategoryOutputDTOTest {

	@Test
	public void constructorWithCategoryShouldCreateCorrectlyObject() {
		Category entity = CategoryMocks.mockCategory();
		
		CategoryOutputDTO result = new CategoryOutputDTO(entity);
		
		CategoryAsserts.assertCategoryOutputDTO(result);
	}
}
