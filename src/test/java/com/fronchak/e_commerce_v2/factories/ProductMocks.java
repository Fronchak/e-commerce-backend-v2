package com.fronchak.e_commerce_v2.factories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.fronchak.e_commerce_v2.dtos.product.ProductInputDTO;
import com.fronchak.e_commerce_v2.dtos.product.ProductInsertDTO;
import com.fronchak.e_commerce_v2.dtos.product.ProductOutputDTO;
import com.fronchak.e_commerce_v2.dtos.product.ProductSimpleOutputDTO;
import com.fronchak.e_commerce_v2.dtos.product.ProductUpdateDTO;
import com.fronchak.e_commerce_v2.entities.Product;

public class ProductMocks {

	public static Product mockProduct() {
		return mockProduct(0);
	}
	
	public static Product mockProduct(int i) {
		Product mock = new Product();
		mock.setId(mockId(i));
		mock.setName(mockName(i));
		mock.setDescription(mockDescription(i));
		mock.setImgUrl(mockImgUrl(i));
		mock.setPrice(mockPrice(i));
		mock.setQuantity(mockQuantity(i));
		mock.setBrand(BrandMocks.mockBrand());
		mock.addCategory(CategoryMocks.mockCategory(0));
		mock.addCategory(CategoryMocks.mockCategory(1));
		mock.addAssessment(AssessmentMocks.mockAssessment(2));
		mock.addAssessment(AssessmentMocks.mockAssessment(4));
		return mock;
	}
	
	private static Long mockId(int i) {
		return i + 20L;
	}
	
	private static String mockName(int i) {
		return "Mock product name " + i;
	}
	
	private static String mockDescription(int i) {
		return "Mock product description " + i;
	}
	
	private static String mockImgUrl(int i) {
		return "http://product" + i + ".jpg";
	}
	
	private static Double mockPrice(int i) {
		return 1.0 + i;
	}
	
	private static Integer mockQuantity(int i) {
		return 2 + i;
	}

	public static ProductSimpleOutputDTO mockProductSimpleOutputDTO() {
		return mockProductSimpleOutputDTO(0);
	}

	public static ProductSimpleOutputDTO mockProductSimpleOutputDTO(int i) {
		ProductSimpleOutputDTO mock = new ProductSimpleOutputDTO();
		mock.setId(mockId(i));
		mock.setName(mockName(i));
		mock.setPrice(mockPrice(i));
		mock.setImgUrl(mockImgUrl(i));
		mock.setGrade(4);
		mock.setNumberOfAssessments(2);
		return mock;
	}
	
	public static ProductOutputDTO mockProductOutputDTO() {
		return mockProductOutputDTO(0);
	}
	
	public static ProductOutputDTO mockProductOutputDTO(int i) {
		ProductOutputDTO mock = new ProductOutputDTO();
		mock.setId(mockId(i));
		mock.setName(mockName(i));
		mock.setDescription(mockDescription(i));
		mock.setImgUrl(mockImgUrl(i));
		mock.setPrice(mockPrice(i));
		mock.setInStock(true);
		mock.setBrand(BrandMocks.mockBrandOutputDTO());
		mock.setCategories(CategoryMocks.mockCategoryNameOutputDTOs());
		mock.setGrade(4);
		mock.setNumberOfAssessments(2);
		return mock;
	}
	
	public static Page<Product> mockProducts() {
		List<Product> list = new ArrayList<>();
		list.add(mockProduct(0));
		list.add(mockProduct(1));
		return new PageImpl<>(list);
	}
	
	public static Page<ProductSimpleOutputDTO> mockProductSimpleOutputDTOs() {
		List<ProductSimpleOutputDTO> list = new ArrayList<>();
		list.add(mockProductSimpleOutputDTO(0));
		list.add(mockProductSimpleOutputDTO(1));
		return new PageImpl<>(list);
	}
	
	public static ProductInputDTO mockProductInputDTO() {
		return mockProductInputDTO(new ProductInputDTO());
	}
	
	private static ProductInputDTO mockProductInputDTO(ProductInputDTO mock) {
		mock.setName(mockName(0));
		mock.setDescription(mockDescription(0));
		mock.setImgUrl(mockImgUrl(0));
		mock.setPrice(mockPrice(0));
		mock.setIdBrand(0L);
		mock.setIdCategories(List.of(10L, 11L));
		return mock;
	}
	
	public static ProductInsertDTO mockProductInsertDTO() {
		return (ProductInsertDTO) mockProductInputDTO(new ProductInsertDTO());
	}
	
	public static ProductUpdateDTO mockProductUpdateDTO() {
		return (ProductUpdateDTO) mockProductInputDTO(new ProductUpdateDTO());
	}
}
