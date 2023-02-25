package com.fronchak.e_commerce_v2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fronchak.e_commerce_v2.dtos.product.ProductInputDTO;
import com.fronchak.e_commerce_v2.dtos.product.ProductInsertDTO;
import com.fronchak.e_commerce_v2.dtos.product.ProductOutputDTO;
import com.fronchak.e_commerce_v2.dtos.product.ProductSimpleOutputDTO;
import com.fronchak.e_commerce_v2.dtos.product.ProductUpdateDTO;
import com.fronchak.e_commerce_v2.entities.Product;
import com.fronchak.e_commerce_v2.exceptions.DatabaseException;
import com.fronchak.e_commerce_v2.exceptions.ResourceNotFoundException;
import com.fronchak.e_commerce_v2.mappers.ProductMapper;
import com.fronchak.e_commerce_v2.repositories.BrandRepository;
import com.fronchak.e_commerce_v2.repositories.CategoryRepository;
import com.fronchak.e_commerce_v2.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

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
		try {
			Product entity = new Product();
			copyDTOToEntity(inputDTO, entity);
			entity = productRepository.save(entity);
			return mapper.convertProductToProductOutputDTO(entity);	
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Error in saving product, brand or categories invalids");
		}
	}
	
	private void copyDTOToEntity(ProductInputDTO dto, Product entity) {
		mapper.copyProductInputDTOToProduct(dto, entity);
		entity.setBrand(brandRepository.getReferenceById(dto.getIdBrand()));
		entity.getCategories().clear();
		dto.getIdCategories().forEach((idCategory) -> {
			entity.addCategory(categoryRepository.getReferenceById(idCategory));
		});
	}
	
	@Transactional
	public ProductOutputDTO update(ProductUpdateDTO updateDTO, Long id) {
		try {
			Product entity = productRepository.getReferenceById(id);
			copyDTOToEntity(updateDTO, entity);
			entity = productRepository.save(entity);
			return mapper.convertProductToProductOutputDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Error updating product");
		}
	}
	
	@Transactional(readOnly = true)
	public ProductOutputDTO findById(Long id) {
		Product entity = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product", id.toString()));
		return mapper.convertProductToProductOutputDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public Page<ProductSimpleOutputDTO> findAllFilter() {
		
		return null;
	}
	
	public void deleteById(Long id) {
		try {
			productRepository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Product", id.toString());
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Product cannot be deleted");
		}
	}
}
