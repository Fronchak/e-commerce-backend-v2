package com.fronchak.e_commerce_v2.factories;

import java.util.ArrayList;
import java.util.List;

import com.fronchak.e_commerce_v2.dtos.category.CategoryInputDTO;
import com.fronchak.e_commerce_v2.dtos.category.CategoryInsertDTO;
import com.fronchak.e_commerce_v2.dtos.category.CategoryNameOutputDTO;
import com.fronchak.e_commerce_v2.dtos.category.CategoryOutputDTO;
import com.fronchak.e_commerce_v2.dtos.category.CategoryUpdateDTO;
import com.fronchak.e_commerce_v2.entities.Category;

public class CategoryMocks {

	public static Category mockCategory() {
		return mockCategory(0);
	}
	
	public static Category mockCategory(int i) {
		Category mock = new Category();
		mock.setId(mockId(i));
		mock.setName(mockName(i));
		mock.setDescription(mockDescription(i));
		mock.setImgUrl(mockImgUrl(i));
		return mock;
	}
	
	private static Long mockId(int i) {
		return i + 10L;
	}
	
	private static String mockName(int i) {
		return "Mock category name " + i;
	}
	
	private static String mockDescription(int i) {
		return "Mock category description " + i;
	}
	
	private static String mockImgUrl(int i) {
		return "http://category" + i + ".jpg";
	}
	
	public static CategoryNameOutputDTO mockCategoryNameOutputDTO() {
		return mockCategoryNameOutputDTO(0);
	}
	
	public static CategoryNameOutputDTO mockCategoryNameOutputDTO(int i) {
		CategoryNameOutputDTO mock = new CategoryNameOutputDTO();
		mock.setId(mockId(i));
		mock.setName(mockName(i));
		mock.setImgUrl(mockImgUrl(i));
		return mock;
	}
	
	public static CategoryOutputDTO mockCategoryOutputDTO() {
		return mockCategoryOutputDTO(0);
	}
	
	public static CategoryOutputDTO mockCategoryOutputDTO(int i) {
		CategoryOutputDTO mock = new CategoryOutputDTO();
		mock.setId(mockId(i));
		mock.setName(mockName(i));
		mock.setDescription(mockDescription(i));
		mock.setImgUrl(mockImgUrl(i));
		return mock;
	}
	
	public static List<Category> mockCategories() {
		List<Category> list = new ArrayList<>();
		list.add(mockCategory(0));
		list.add(mockCategory(1));
		return list;
	}
	
	public static List<CategoryNameOutputDTO> mockCategoryNameOutputDTOs() {
		List<CategoryNameOutputDTO> list = new ArrayList<>();
		list.add(mockCategoryNameOutputDTO(0));
		list.add(mockCategoryNameOutputDTO(1));
		return list;
	}
	
	public static CategoryInputDTO mockCategoryInputDTO() {
		return mockCategoryInputDTO(new CategoryInputDTO());
	}
	
	private static CategoryInputDTO mockCategoryInputDTO(CategoryInputDTO mock) {
		mock.setName(mockName(0));
		mock.setImgUrl(mockImgUrl(0));
		mock.setDescription(mockDescription(0));
		return mock;
	}
	
	public static CategoryInsertDTO mockCategoryInsertDTO() {
		return (CategoryInsertDTO) mockCategoryInputDTO(new CategoryInsertDTO());
	}
	
	public static CategoryUpdateDTO mockCategoryUpdateDTO() {
		return (CategoryUpdateDTO) mockCategoryInputDTO(new CategoryUpdateDTO());
	}
}
