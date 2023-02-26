package com.fronchak.e_commerce_v2.mappers;

import static com.fronchak.e_commerce_v2.factories.ProductMocks.mockProduct;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.e_commerce_v2.dtos.brand.BrandOutputDTO;
import com.fronchak.e_commerce_v2.dtos.category.CategoryNameOutputDTO;
import com.fronchak.e_commerce_v2.dtos.product.ProductInputDTO;
import com.fronchak.e_commerce_v2.dtos.product.ProductOutputDTO;
import com.fronchak.e_commerce_v2.dtos.product.ProductSimpleOutputDTO;
import com.fronchak.e_commerce_v2.entities.Product;
import com.fronchak.e_commerce_v2.factories.BrandMocks;
import com.fronchak.e_commerce_v2.factories.CategoryMocks;
import com.fronchak.e_commerce_v2.factories.ProductMocks;
import com.fronchak.e_commerce_v2util.ProductAsserts;

@ExtendWith(SpringExtension.class)
public class ProductMapperTest {

	@Mock
	private BrandMapper brandMapper;
	
	@Mock
	private CategoryMapper categoryMapper;
	
	@InjectMocks
	private ProductMapper productMapper;
	
	private Product entity;
	
	@BeforeEach
	void setUp() {
		entity = mockProduct();
	}
	
	@Test
	public void convertProductToProductOutputDTOShouldConvertCorrectly() {
		BrandOutputDTO brand = BrandMocks.mockBrandOutputDTO();
		List<CategoryNameOutputDTO> categories = CategoryMocks.mockCategoryNameOutputDTOs();
		
		when(brandMapper.convertBrandToBrandOutputDTO(entity.getBrand())).thenReturn(brand);
		when(categoryMapper.convertCategoriesToCategoryNameOutputDTOs(entity.getCategories())).thenReturn(categories);
		
		ProductOutputDTO result = productMapper.convertProductToProductOutputDTO(entity);
		
		ProductAsserts.assertProductOutputDTO(result);
		assertSame(brand, result.getBrand());
		assertSame(categories, result.getCategories());
	}
	
	@Test
	public void convertProductToProductSimpleOutputDTOShouldConvertCorrectly() {
		ProductSimpleOutputDTO result = productMapper.convertProductToProductSimpleOutputDTO(entity);
		
		ProductAsserts.assertProductSimpleOutputDTO_0(result);
	}
	
	@Test
	public void convertProductsToProductSimpleOutputDTOsShouldConvertCorrectly() {
		Page<Product> entities = ProductMocks.mockProducts(); 
		
		Page<ProductSimpleOutputDTO> result = productMapper.convertProductsToProductSimpleOutputDTOs(entities);
		
		ProductAsserts.assertProductSimpleOutputDTOs(result);
	}
	
	@Test
	public void copyProductInputDTOToProductShouldCopyValuesCorrectly() {
		Product entity = new Product();
		entity.setId(2L);
		entity.setQuantity(3);
		ProductInputDTO inputDTO = ProductMocks.mockProductInputDTO();
		
		productMapper.copyProductInputDTOToProduct(inputDTO, entity);
		
		assertEquals(2L, entity.getId());
		assertEquals(3, entity.getQuantity());
		assertEquals("Mock product name 0", entity.getName());
		assertEquals("Mock product description 0", entity.getDescription());
		assertEquals("http://product0.jpg", entity.getImgUrl());
		assertEquals(1.0, entity.getPrice());
	}
}
