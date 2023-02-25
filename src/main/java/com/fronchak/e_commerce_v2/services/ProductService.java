package com.fronchak.e_commerce_v2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fronchak.e_commerce_v2.dtos.product.ProductInsertDTO;
import com.fronchak.e_commerce_v2.dtos.product.ProductOutputDTO;
import com.fronchak.e_commerce_v2.mappers.ProductMapper;
import com.fronchak.e_commerce_v2.repositories.BrandRepository;
import com.fronchak.e_commerce_v2.repositories.CategoryRepository;
import com.fronchak.e_commerce_v2.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductMapper mapper;
	
	@Transactional
	public ProductOutputDTO save(ProductInsertDTO inputDTO) {
		
		return null;
	}
}
