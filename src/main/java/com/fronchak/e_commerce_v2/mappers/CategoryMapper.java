package com.fronchak.e_commerce_v2.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fronchak.e_commerce_v2.dtos.category.CategoryInputDTO;
import com.fronchak.e_commerce_v2.dtos.category.CategoryNameOutputDTO;
import com.fronchak.e_commerce_v2.dtos.category.CategoryOutputDTO;
import com.fronchak.e_commerce_v2.entities.Category;

@Service
public class CategoryMapper {

	public CategoryOutputDTO convertCategoryToCategoryOutputDTO(Category entity) {
		return new CategoryOutputDTO(entity);
	}
	
	public List<CategoryNameOutputDTO> convertCategoriesToCategoryNameOutputDTOs(List<Category> entities) {
		return entities.stream()
				.map((entity) -> convertCategoryToCategoryNameOutputDTO(entity))
				.collect(Collectors.toList());
	}
	
	public CategoryNameOutputDTO convertCategoryToCategoryNameOutputDTO(Category entity) {
		return new CategoryNameOutputDTO(entity);
	}
	
	public void copyCategoryInputDTOToCategory(CategoryInputDTO dto, Category entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setImgUrl(dto.getImgUrl());
	}
}
