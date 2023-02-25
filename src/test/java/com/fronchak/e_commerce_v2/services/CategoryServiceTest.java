package com.fronchak.e_commerce_v2.services;

import static com.fronchak.e_commerce_v2.factories.CategoryMocks.mockCategories;
import static com.fronchak.e_commerce_v2.factories.CategoryMocks.mockCategory;
import static com.fronchak.e_commerce_v2.factories.CategoryMocks.mockCategoryInsertDTO;
import static com.fronchak.e_commerce_v2.factories.CategoryMocks.mockCategoryNameOutputDTOs;
import static com.fronchak.e_commerce_v2.factories.CategoryMocks.mockCategoryOutputDTO;
import static com.fronchak.e_commerce_v2.factories.CategoryMocks.mockCategoryUpdateDTO;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {

	private static final Long VALID_ID = 1L;
	private static final Long INVALID_ID = 2L;
	private static final Long DEPENDENT_ID = 3L;
	
	@Mock
	private CategoryRepository repository;
	
	@Mock
	private CategoryMapper mapper;
	
	@InjectMocks
	private CategoryService service;
	
	private Category entity;
	private CategoryOutputDTO outputDTO;

	@BeforeEach
	void setUp() {
		entity = mockCategory();
		outputDTO = mockCategoryOutputDTO();
	}
	
	@Test
	public void saveShouldReturnCategoryOutputDTOAfterSaveEntity() {
		CategoryInsertDTO inputDTO = mockCategoryInsertDTO();
		
		when(repository.save(any(Category.class))).thenReturn(entity);
		when(mapper.convertCategoryToCategoryOutputDTO(entity)).thenReturn(outputDTO);
		
		CategoryOutputDTO result = service.save(inputDTO);
		
		assertSame(outputDTO, result);
		verify(mapper, times(1)).copyCategoryInputDTOToCategory(eq(inputDTO), any(Category.class));
	}
	
	@Test
	public void updateShouldReturnCategoryOutputDTOWhenIdExists() {
		Category entityAfterSave = mockCategory();
		CategoryUpdateDTO inputDTO = mockCategoryUpdateDTO();
		
		when(repository.getReferenceById(VALID_ID)).thenReturn(entity);
		when(repository.save(entity)).thenReturn(entityAfterSave);
		when(mapper.convertCategoryToCategoryOutputDTO(entityAfterSave)).thenReturn(outputDTO);
		
		CategoryOutputDTO result = service.update(inputDTO, VALID_ID);
		
		assertSame(outputDTO, result);
		verify(mapper, times(1)).copyCategoryInputDTOToCategory(inputDTO, entity);
	}
	
	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		CategoryUpdateDTO inputDTO = mockCategoryUpdateDTO();
		
		when(repository.getReferenceById(INVALID_ID)).thenThrow(EntityNotFoundException.class);
		
		assertThrows(ResourceNotFoundException.class, () -> service.update(inputDTO, INVALID_ID));
		
		verify(repository, times(1)).getReferenceById(INVALID_ID);
		verify(repository, never()).save(any());
	}
	
	@Test
	public void findByIdShouldReturnCategoryOutputDTOWhenIdExists() {		
		when(repository.findById(VALID_ID)).thenReturn(Optional.of(entity));
		when(mapper.convertCategoryToCategoryOutputDTO(entity)).thenReturn(outputDTO);
		
		CategoryOutputDTO result = service.findById(VALID_ID);
		
		assertSame(outputDTO, result);
	}
	
	@Test
	public void findByIdShouldReturnResourceNotFoundExceptionWhenIdDoesNotExist() {
		when(repository.findById(INVALID_ID)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, () -> service.findById(INVALID_ID));
		
		verify(repository, times(1)).findById(INVALID_ID);
	}
	
	@Test
	public void findAllShouldReturnCategoryNameOutputDTOs() {
		List<Category> entities = mockCategories();
		List<CategoryNameOutputDTO> dtos = mockCategoryNameOutputDTOs();
		
		when(repository.findAll()).thenReturn(entities);
		when(mapper.convertCategoriesToCategoryNameOutputDTOs(entities)).thenReturn(dtos);
		
		List<CategoryNameOutputDTO> result = service.findAll();
		
		assertSame(dtos, result);
	}
	
	@Test
	public void deleteShouldReturnNothingWhenIdExistsAndEntityCanBeDeleted() {
		doNothing().when(repository).deleteById(VALID_ID);
		
		service.deleteById(VALID_ID);
		
		verify(repository, times(1)).deleteById(VALID_ID);
	}
	
	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(INVALID_ID);
		
		assertThrows(ResourceNotFoundException.class, () -> service.deleteById(INVALID_ID));
		
		verify(repository, times(1)).deleteById(INVALID_ID);
	}
	
	@Test
	public void deleteShouldThrowDatabaseExceptionWhenIdExistsButCanNotBeDeleted() {
		doThrow(DataIntegrityViolationException.class).when(repository).deleteById(DEPENDENT_ID);
		
		assertThrows(DatabaseException.class, () -> service.deleteById(DEPENDENT_ID));
		
		verify(repository, times(1)).deleteById(DEPENDENT_ID);
	}
}
