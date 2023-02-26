package com.fronchak.e_commerce_v2.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.e_commerce_v2.dtos.brand.BrandInputDTO;
import com.fronchak.e_commerce_v2.dtos.brand.BrandOutputDTO;
import com.fronchak.e_commerce_v2.entities.Brand;
import com.fronchak.e_commerce_v2.factories.BrandMocks;
import com.fronchak.e_commerce_v2util.BrandAsserts;

@ExtendWith(SpringExtension.class)
public class BrandMapperTest {

	private BrandMapper mapper;
	
	@BeforeEach
	void setUp() {
		mapper = new BrandMapper();
	}
	
	@Test
	public void convertBrandToBrandOutputDTOShouldConvertCorrectly() {
		Brand entity = BrandMocks.mockBrand();
		
		BrandOutputDTO result = mapper.convertBrandToBrandOutputDTO(entity);
		
		BrandAsserts.assertBrandOutputDTO_0(result);
	}
	
	@Test
	public void convertBrandListToBrandOutputDTOListShouldConvertCorrectly() {
		List<Brand> list = BrandMocks.mockBrands();
		
		List<BrandOutputDTO> resultList = mapper.convertBrandsToBrandOutputDTOs(list);
		
		BrandAsserts.assertBrandOutputDTOList(resultList);
	}
	
	@Test
	public void copyBrandInputDTOToBrandShouldCopyCorrectly() {
		Brand entity = new Brand();
		entity.setId(10L);
		BrandInputDTO inputDTO = BrandMocks.mockBrandInputDTO();
		
		mapper.copyBrandInputDTOToBrand(inputDTO, entity);
		
		assertEquals(10, entity.getId());
		assertEquals("Mock brand name 0", entity.getName());
		assertEquals("http://brand0.jpg", entity.getImgUrl());
	}
}
