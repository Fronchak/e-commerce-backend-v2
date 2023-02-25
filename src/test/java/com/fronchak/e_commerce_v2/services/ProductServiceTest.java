package com.fronchak.e_commerce_v2.services;

import static com.fronchak.e_commerce_v2.factories.ProductMocks.mockProduct;
import static com.fronchak.e_commerce_v2.factories.ProductMocks.mockProductOutputDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.e_commerce_v2.dtos.product.ProductInsertDTO;
import com.fronchak.e_commerce_v2.dtos.product.ProductOutputDTO;
import com.fronchak.e_commerce_v2.dtos.product.ProductUpdateDTO;
import com.fronchak.e_commerce_v2.entities.Brand;
import com.fronchak.e_commerce_v2.entities.Category;
import com.fronchak.e_commerce_v2.entities.Product;
import com.fronchak.e_commerce_v2.exceptions.DatabaseException;
import com.fronchak.e_commerce_v2.exceptions.ResourceNotFoundException;
import com.fronchak.e_commerce_v2.factories.BrandMocks;
import com.fronchak.e_commerce_v2.factories.CategoryMocks;
import com.fronchak.e_commerce_v2.factories.ProductMocks;
import com.fronchak.e_commerce_v2.mappers.ProductMapper;
import com.fronchak.e_commerce_v2.repositories.BrandRepository;
import com.fronchak.e_commerce_v2.repositories.CategoryRepository;
import com.fronchak.e_commerce_v2.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

	private static final Long VALID_ID = 1L;
	private static final Long INVALID_ID = 2L;
	private static final Long DEPENDENT_ID = 3L;
	
	@Mock
	private ProductRepository productRepository;
	
	@Mock
	private BrandRepository brandRepository;
	
	@Mock
	private CategoryRepository categoryRepository;
	
	@Mock
	private ProductMapper mapper;
	
	@InjectMocks
	private ProductService service;
	
	private Product entity;
	private ProductOutputDTO outputDTO;
	
	@BeforeEach
	void setUp() {
		entity = mockProduct();
		outputDTO = mockProductOutputDTO();
	}
	
	@Test
	public void saveShouldReturnProductOutputDTOAfterSaveEntity() {
		Brand brand = BrandMocks.mockBrand();
		Category category0 = CategoryMocks.mockCategory(10);
		Category category1 = CategoryMocks.mockCategory(11);
		ProductInsertDTO inputDTO = ProductMocks.mockProductInsertDTO();
		ArgumentCaptor<Product> argumentCaptor = ArgumentCaptor.forClass(Product.class);
		
		when(brandRepository.getReferenceById(inputDTO.getIdBrand())).thenReturn(brand);
		when(categoryRepository.getReferenceById(inputDTO.getIdCategories().get(0))).thenReturn(category0);
		when(categoryRepository.getReferenceById(inputDTO.getIdCategories().get(1))).thenReturn(category1);
		when(productRepository.save(any(Product.class))).thenReturn(entity);
		when(mapper.convertProductToProductOutputDTO(entity)).thenReturn(outputDTO);
		
		ProductOutputDTO result = service.save(inputDTO);
		verify(productRepository).save(argumentCaptor.capture());
		Product entitySaved = argumentCaptor.getValue();
		
		assertSame(outputDTO, result);
		verify(mapper, times(1)).copyProductInputDTOToProduct(inputDTO, entitySaved);
		assertEquals(0, entitySaved.getQuantity());
		assertSame(brand, entitySaved.getBrand());
		Set<Category> categories = entitySaved.getCategories();
		assertEquals(2, categories.size());
		assertTrue(categories.contains(category0));
		assertTrue(categories.contains(category1));
	}
	
	@Test
	public void saveShouldReturnResourceNotFoundExceptionWhenBrandIdIsInvalid() {
		ProductInsertDTO inputDTO = ProductMocks.mockProductInsertDTO();
		
		when(brandRepository.getReferenceById(inputDTO.getIdBrand())).thenThrow(EntityNotFoundException.class);
		
		assertThrows(ResourceNotFoundException.class, () -> service.save(inputDTO));
		verify(brandRepository, times(1)).getReferenceById(inputDTO.getIdBrand());
		verify(productRepository, never()).save(any());
	}
	
	@Test
	public void saveShouldReturnResourceNotFoundExceptionWhenCategoryIdIsInvalid() {
		ProductInsertDTO inputDTO = ProductMocks.mockProductInsertDTO();
		
		when(categoryRepository.getReferenceById(inputDTO.getIdCategories().get(0))).thenThrow(EntityNotFoundException.class);
		
		assertThrows(ResourceNotFoundException.class, () -> service.save(inputDTO));
		verify(categoryRepository, times(1)).getReferenceById(inputDTO.getIdCategories().get(0));
		verify(productRepository, never()).save(any());
	}
	
	@Test
	public void updateShouldReturnProductOutputDTOWhenIdExists() {
		Brand brand = BrandMocks.mockBrand();
		Category category0 = CategoryMocks.mockCategory(10);
		Category category1 = CategoryMocks.mockCategory(11);
		ProductUpdateDTO inputDTO = ProductMocks.mockProductUpdateDTO();
		ArgumentCaptor<Product> argumentCaptor = ArgumentCaptor.forClass(Product.class);
		Product entityAfterSave = mockProduct();
		
		when(productRepository.getReferenceById(VALID_ID)).thenReturn(entity);
		when(brandRepository.getReferenceById(inputDTO.getIdBrand())).thenReturn(brand);
		when(categoryRepository.getReferenceById(inputDTO.getIdCategories().get(0))).thenReturn(category0);
		when(categoryRepository.getReferenceById(inputDTO.getIdCategories().get(1))).thenReturn(category1);
		when(productRepository.save(entity)).thenReturn(entityAfterSave);
		when(mapper.convertProductToProductOutputDTO(entityAfterSave)).thenReturn(outputDTO);
		
		ProductOutputDTO result = service.update(inputDTO, VALID_ID);
		verify(productRepository).save(argumentCaptor.capture());
		Product entitySaved = argumentCaptor.getValue();
		
		assertSame(outputDTO, result);
		verify(mapper, times(1)).copyProductInputDTOToProduct(inputDTO, entitySaved);
		assertSame(brand, entitySaved.getBrand());
		Set<Category> categories = entitySaved.getCategories();
		assertEquals(2, categories.size());
		assertTrue(categories.contains(category0));
		assertTrue(categories.contains(category1));
	}
	
	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		ProductUpdateDTO inputDTO = ProductMocks.mockProductUpdateDTO();
		
		when(productRepository.getReferenceById(INVALID_ID)).thenThrow(EntityNotFoundException.class);
		
		assertThrows(ResourceNotFoundException.class, () -> service.update(inputDTO, INVALID_ID));
		
		verify(productRepository, times(1)).getReferenceById(INVALID_ID);
		verify(productRepository, never()).save(any());
	}
	
	@Test
	public void updateShouldReturnResourceNotFoundExceptionWhenBrandIdIsInvalid() {
		ProductUpdateDTO inputDTO = ProductMocks.mockProductUpdateDTO();
		
		when(brandRepository.getReferenceById(inputDTO.getIdBrand())).thenThrow(EntityNotFoundException.class);
		
		assertThrows(ResourceNotFoundException.class, () -> service.update(inputDTO, VALID_ID));
		verify(brandRepository, times(1)).getReferenceById(inputDTO.getIdBrand());
		verify(productRepository, never()).save(any());
	}
	
	@Test
	public void updateShouldReturnResourceNotFoundExceptionWhenCategoryIdIsInvalid() {
		ProductUpdateDTO inputDTO = ProductMocks.mockProductUpdateDTO();
		
		when(productRepository.getReferenceById(VALID_ID)).thenReturn(entity);
		when(categoryRepository.getReferenceById(inputDTO.getIdCategories().get(0))).thenThrow(EntityNotFoundException.class);
		
		assertThrows(ResourceNotFoundException.class, () -> service.update(inputDTO, VALID_ID));
		verify(categoryRepository, times(1)).getReferenceById(inputDTO.getIdCategories().get(0));
		verify(productRepository, never()).save(any());
	}
	
	@Test
	public void findByIdShouldReturnProductOutputDTOWhenIdExists() {
		when(productRepository.findById(VALID_ID)).thenReturn(Optional.of(entity));
		when(mapper.convertProductToProductOutputDTO(entity)).thenReturn(outputDTO);
		
		ProductOutputDTO result = service.findById(VALID_ID);
		
		assertSame(outputDTO, result);
	}
	
	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		when(productRepository.findById(INVALID_ID)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, () -> service.findById(INVALID_ID));
		
		verify(productRepository, times(1)).findById(INVALID_ID);
	}
	
	@Test
	public void deleteShouldReturnNothingWhenIdIsValidAndEntityCanBeDeleted() {
		service.deleteById(VALID_ID);
		
		verify(productRepository, times(1)).deleteById(VALID_ID);
	}
	
	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		doThrow(EmptyResultDataAccessException.class).when(productRepository).deleteById(INVALID_ID);
		
		assertThrows(ResourceNotFoundException.class, () -> service.deleteById(INVALID_ID));
		
		verify(productRepository, times(1)).deleteById(INVALID_ID);
	}
	
	@Test
	public void deleteShouldThrowDatabaseExceptionWhenIdExistsButEntityCannotBeDeleted() {
		doThrow(DataIntegrityViolationException.class).when(productRepository).deleteById(DEPENDENT_ID);
		
		assertThrows(DatabaseException.class, () -> service.deleteById(DEPENDENT_ID));
		
		verify(productRepository, times(1)).deleteById(DEPENDENT_ID);
	}
}
