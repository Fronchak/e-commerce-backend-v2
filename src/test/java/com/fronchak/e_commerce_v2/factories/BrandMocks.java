package com.fronchak.e_commerce_v2.factories;

import java.util.ArrayList;
import java.util.List;

import com.fronchak.e_commerce_v2.dtos.brand.BrandInputDTO;
import com.fronchak.e_commerce_v2.dtos.brand.BrandInsertDTO;
import com.fronchak.e_commerce_v2.dtos.brand.BrandOutputDTO;
import com.fronchak.e_commerce_v2.dtos.brand.BrandUpdateDTO;
import com.fronchak.e_commerce_v2.entities.Brand;

public class BrandMocks {

	public static Brand mockBrand() {
		return mockBrand(0);
	}
	
	public static Brand mockBrand(int i) {
		Brand mock = new Brand();
		mock.setId(mockId(i));
		mock.setName(mockName(i));
		mock.setImgUrl(mockImgUrl(i));
		return mock;
	}
	
	private static Long mockId(int i) {
		return i + 0L;
	}
	
	private static String mockName(int i) {
		return "Mock brand name " + i;
	}
	
	private static String mockImgUrl(int i) {
		return "http://brand" + i + ".jpg";
	}
	
	public static BrandOutputDTO mockBrandOutputDTO() {
		return mockBrandOutputDTO(0);
	}
	
	public static BrandOutputDTO mockBrandOutputDTO(int i) {
		BrandOutputDTO mock = new BrandOutputDTO();
		mock.setId(mockId(i));
		mock.setName(mockName(i));
		mock.setImgUrl(mockImgUrl(i));
		return mock;
	}
	
	public static List<Brand> mockBrands() {
		List<Brand> list = new ArrayList<>();
		list.add(mockBrand(0));
		list.add(mockBrand(1));
		return list;
	}
	
	public static List<BrandOutputDTO> mockBrandOutputDTOList() {
		List<BrandOutputDTO> list = new ArrayList<>();
		list.add(mockBrandOutputDTO(0));
		list.add(mockBrandOutputDTO(1));
		return list;
	}
	
	public static BrandInputDTO mockBrandInputDTO() {
		return mockBrandInputDTO(new BrandInputDTO());
	}
	
	private static BrandInputDTO mockBrandInputDTO(BrandInputDTO mock) {
		mock.setName(mockName(0));
		mock.setImgUrl(mockImgUrl(0));
		return mock;
	}
	
	public static BrandInsertDTO mockBrandInsertDTO() {
		return (BrandInsertDTO) mockBrandInputDTO(new BrandInsertDTO());
	}
	
	public static BrandUpdateDTO mockBrandUpdateDTO() {
		return (BrandUpdateDTO) mockBrandInputDTO(new BrandUpdateDTO());
	}
}
