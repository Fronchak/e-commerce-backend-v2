package com.fronchak.e_commerce_v2.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.e_commerce_v2.dtos.brand.BrandInsertDTO;
import com.fronchak.e_commerce_v2.dtos.brand.BrandOutputDTO;
import com.fronchak.e_commerce_v2.dtos.brand.BrandUpdateDTO;
import com.fronchak.e_commerce_v2.entities.Brand;
import com.fronchak.e_commerce_v2.exceptions.ResourceNotFoundException;
import com.fronchak.e_commerce_v2.factories.BrandMocks;
import com.fronchak.e_commerce_v2.mappers.BrandMapper;
import com.fronchak.e_commerce_v2.repositories.BrandRepository;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
public class BrandServiceTest {

	private static final Long VALID_ID = 1L;
	private static final Long INVALID_ID = 2L;
	
	@Mock
	private BrandRepository repository;
	
	@Mock
	private BrandMapper mapper;
	
	@InjectMocks
	private BrandService service;
	
	@Test
	public void saveShouldSaveEntityAndReturnBrandOutputDTO() {
		BrandInsertDTO inputDTO = BrandMocks.mockBrandInsertDTO();
		BrandOutputDTO outputDTO = BrandMocks.mockBrandOutputDTO();
		Brand entity = BrandMocks.mockBrand();
		
		when(repository.save(any(Brand.class))).thenReturn(entity);
		when(mapper.convertBrandToBrandOutputDTO(entity)).thenReturn(outputDTO);
		
		BrandOutputDTO result = service.save(inputDTO);
		
		assertSame(outputDTO, result);
		verify(mapper, times(1)).copyBrandInputDTOToBrand(eq(inputDTO), any(Brand.class));
	}
	
	@Test
	public void updateShouldUpdateEntityAndReturnBrandOutputDTOWhenIdIsValid() {
		Brand entityBeforeSave = BrandMocks.mockBrand();
		Brand entityAfterSave = BrandMocks.mockBrand();
		BrandOutputDTO outputDTO = BrandMocks.mockBrandOutputDTO();
		BrandUpdateDTO inputDTO = BrandMocks.mockBrandUpdateDTO();
		
		when(repository.getReferenceById(VALID_ID)).thenReturn(entityBeforeSave);
		when(repository.save(entityBeforeSave)).thenReturn(entityAfterSave);
		when(mapper.convertBrandToBrandOutputDTO(entityAfterSave)).thenReturn(outputDTO);
		
		BrandOutputDTO result = service.update(inputDTO, VALID_ID);
		
		verify(mapper, times(1)).copyBrandInputDTOToBrand(inputDTO, entityBeforeSave);
		assertSame(outputDTO, result);
	}
	
	@Test
	public void updateShouldThrowResourceNotFoundWhenIdIsInvalid() {
		BrandUpdateDTO inputDTO = BrandMocks.mockBrandUpdateDTO();
		
		when(repository.getReferenceById(INVALID_ID)).thenThrow(EntityNotFoundException.class);
		
		assertThrows(ResourceNotFoundException.class, () -> service.update(inputDTO, INVALID_ID));
		verify(repository, never()).save(any());
	}
	
	@Test
	public void findByIdShouldReturnBrandOutputDTOWhenIdExists() {
		Brand entity = BrandMocks.mockBrand();
		BrandOutputDTO outputDTO = BrandMocks.mockBrandOutputDTO();
		
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
}
