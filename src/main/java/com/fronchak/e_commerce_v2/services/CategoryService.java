package com.fronchak.e_commerce_v2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fronchak.e_commerce_v2.dtos.category.CategoryInsertDTO;
import com.fronchak.e_commerce_v2.dtos.category.CategoryNameOutputDTO;
import com.fronchak.e_commerce_v2.dtos.category.CategoryOutputDTO;
import com.fronchak.e_commerce_v2.dtos.category.CategoryUpdateDTO;
import com.fronchak.e_commerce_v2.entities.Category;
import com.fronchak.e_commerce_v2.exceptions.DatabaseException;
import com.fronchak.e_commerce_v2.exceptions.ResourceNotFoundException;
import com.fronchak.e_commerce_v2.mappers.CategoryMapper;
import com.fronchak.e_commerce_v2.repositories.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	@Autowired
	private CategoryMapper mapper;
	
	@Transactional
	public CategoryOutputDTO save(CategoryInsertDTO inputDTO) {
		Category entity = new Category();
		mapper.copyCategoryInputDTOToCategory(inputDTO, entity);
		entity = repository.save(entity);
		return mapper.convertCategoryToCategoryOutputDTO(entity);
	}
	
	@Transactional
	public CategoryOutputDTO update(CategoryUpdateDTO inputDTO, Long id) {
		try {
			Category entity = repository.getReferenceById(id);
			mapper.copyCategoryInputDTOToCategory(inputDTO, entity);
			entity = repository.save(entity);
			return mapper.convertCategoryToCategoryOutputDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Category", id.toString());
		}
	}
	
	@Transactional(readOnly = true)
	public CategoryOutputDTO findById(Long id) {
		Category entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", id.toString()));
		return mapper.convertCategoryToCategoryOutputDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public List<CategoryNameOutputDTO> findAll() {
		List<Category> entities = repository.findAll();
		return mapper.convertCategoriesToCategoryNameOutputDTOs(entities);
	}
	
	public void deleteById(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Category", id.toString());
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Category cannot be deleted");
		}
	}
}
