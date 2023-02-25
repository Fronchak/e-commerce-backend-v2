package com.fronchak.e_commerce_v2.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.e_commerce_v2.dtos.category.CategoryInputDTO;
import com.fronchak.e_commerce_v2.dtos.category.CategoryNameOutputDTO;
import com.fronchak.e_commerce_v2.dtos.category.CategoryOutputDTO;
import com.fronchak.e_commerce_v2.entities.Category;
import com.fronchak.e_commerce_v2.factories.CategoryMocks;
import com.fronchak.e_commerce_v2util.CategoryAsserts;

@ExtendWith(SpringExtension.class)
public class CategoryMapperTest {

	private CategoryMapper mapper;
	
	@BeforeEach
	void setUp() {
		mapper = new CategoryMapper();
	}
	
	@Test
	public void convertCategoryToCategoryOutputDTOShouldConvertCorrectly() {
		Category entity = CategoryMocks.mockCategory();
		
		CategoryOutputDTO result = mapper.convertCategoryToCategoryOutputDTO(entity);
		
		CategoryAsserts.assertCategoryOutputDTO(result);
	}
	
	@Test
	public void convertCategoryToCategoryNameOutputDTOShouldConvertCorrectly() {
		Category entity = CategoryMocks.mockCategory();
		
		CategoryNameOutputDTO result = mapper.convertCategoryToCategoryNameOutputDTO(entity);
		
		CategoryAsserts.assertCategoryNameOutputDTO_0(result);
	}
	
	@Test
	public void convertCategoriesToCategoryNameOutputDTOsShouldConvertCorrectly() {
		List<Category> entities = CategoryMocks.mockCategories();
		
		List<CategoryNameOutputDTO> results = mapper.convertCategoriesToCategoryNameOutputDTOs(entities);
		
		CategoryAsserts.assertCategoryNameOutputDTOs(results);
	}
	
	@Test
	public void copyCategoryInputDTOToCategoryShouldCopyValuesCorrectly() {
		Category entity = new Category();
		entity.setId(2L);
		CategoryInputDTO dto = CategoryMocks.mockCategoryInputDTO();
		
		mapper.copyCategoryInputDTOToCategory(dto, entity);
		
		assertEquals(2L, entity.getId());
		CategoryAsserts.assertCategoryProperties(entity);
	}
}
