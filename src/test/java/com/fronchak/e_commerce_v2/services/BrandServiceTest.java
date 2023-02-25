package com.fronchak.e_commerce_v2.services;

import static com.fronchak.e_commerce_v2.factories.BrandMocks.mockBrand;
import static com.fronchak.e_commerce_v2.factories.BrandMocks.mockBrandInsertDTO;
import static com.fronchak.e_commerce_v2.factories.BrandMocks.mockBrandOutputDTO;
import static com.fronchak.e_commerce_v2.factories.BrandMocks.mockBrandOutputDTOList;
import static com.fronchak.e_commerce_v2.factories.BrandMocks.mockBrandUpdateDTO;
import static com.fronchak.e_commerce_v2.factories.BrandMocks.mockBrands;
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

import com.fronchak.e_commerce_v2.dtos.brand.BrandInsertDTO;
import com.fronchak.e_commerce_v2.dtos.brand.BrandOutputDTO;
import com.fronchak.e_commerce_v2.dtos.brand.BrandUpdateDTO;
import com.fronchak.e_commerce_v2.entities.Brand;
import com.fronchak.e_commerce_v2.exceptions.DatabaseException;
import com.fronchak.e_commerce_v2.exceptions.ResourceNotFoundException;
import com.fronchak.e_commerce_v2.mappers.BrandMapper;
import com.fronchak.e_commerce_v2.repositories.BrandRepository;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
public class BrandServiceTest {

	private static final Long VALID_ID = 1L;
	private static final Long INVALID_ID = 2L;
	private static final Long DEPENDENT_ID = 3L;
	
	@Mock
	private BrandRepository repository;
	
	@Mock
	private BrandMapper mapper;
	
	@InjectMocks
	private BrandService service;
	
	private Brand entity;
	private BrandOutputDTO outputDTO;
	
	@BeforeEach
	void setUp() {
		entity = mockBrand();
		outputDTO = mockBrandOutputDTO();
	}
	
	@Test
	public void saveShouldSaveEntityAndReturnBrandOutputDTO() {
		BrandInsertDTO inputDTO = mockBrandInsertDTO();
		
		when(repository.save(any(Brand.class))).thenReturn(entity);
		when(mapper.convertBrandToBrandOutputDTO(entity)).thenReturn(outputDTO);
		
		BrandOutputDTO result = service.save(inputDTO);
		
		assertSame(outputDTO, result);
		verify(mapper, times(1)).copyBrandInputDTOToBrand(eq(inputDTO), any(Brand.class));
	}
	
	@Test
	public void updateShouldUpdateEntityAndReturnBrandOutputDTOWhenIdIsValid() {
		Brand entityAfterSave = mockBrand();
		BrandUpdateDTO inputDTO = mockBrandUpdateDTO();
		
		when(repository.getReferenceById(VALID_ID)).thenReturn(entity);
		when(repository.save(entity)).thenReturn(entityAfterSave);
		when(mapper.convertBrandToBrandOutputDTO(entityAfterSave)).thenReturn(outputDTO);
		
		BrandOutputDTO result = service.update(inputDTO, VALID_ID);
		
		verify(mapper, times(1)).copyBrandInputDTOToBrand(inputDTO, entity);
		assertSame(outputDTO, result);
	}
	
	@Test
	public void updateShouldThrowResourceNotFoundWhenIdIsInvalid() {
		BrandUpdateDTO inputDTO = mockBrandUpdateDTO();
		
		when(repository.getReferenceById(INVALID_ID)).thenThrow(EntityNotFoundException.class);
		
		assertThrows(ResourceNotFoundException.class, () -> service.update(inputDTO, INVALID_ID));
		verify(repository, never()).save(any());
	}
	
	@Test
	public void findByIdShouldReturnBrandOutputDTOWhenIdExists() {		
		when(repository.findById(VALID_ID)).thenReturn(Optional.of(entity));
		when(mapper.convertBrandToBrandOutputDTO(entity)).thenReturn(outputDTO);
		
		BrandOutputDTO result = service.findById(VALID_ID);
		
		assertSame(outputDTO, result);
	}
	
	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdIsInvalid() {
		when(repository.findById(INVALID_ID)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, () -> service.findById(INVALID_ID));
	}
	
	@Test
	public void findAllShouldReturnBrandOutputDTOList() {
		List<Brand> entityList = mockBrands();
		List<BrandOutputDTO> outputDTOList = mockBrandOutputDTOList();
		
		when(repository.findAll()).thenReturn(entityList);
		when(mapper.convertBrandsToBrandOutputDTOs(entityList)).thenReturn(outputDTOList);
		
		List<BrandOutputDTO> resultList = service.findAll();
		
		assertSame(outputDTOList, resultList);
	}
	
	@Test
	public void deleteShouldReturnNothingWhenIdIsValidAndEntityCanBeDeleted() {
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
	public void deleteShouldThrowDatabaseExceptionWhenIdExistButEntityCanNotBeDeleted() {
		doThrow(DataIntegrityViolationException.class).when(repository).deleteById(DEPENDENT_ID);
		
		assertThrows(DatabaseException.class, () -> service.deleteById(DEPENDENT_ID));
		
		verify(repository, times(1)).deleteById(DEPENDENT_ID);
	}
}
