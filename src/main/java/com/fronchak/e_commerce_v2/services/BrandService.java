package com.fronchak.e_commerce_v2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fronchak.e_commerce_v2.dtos.brand.BrandInsertDTO;
import com.fronchak.e_commerce_v2.dtos.brand.BrandOutputDTO;
import com.fronchak.e_commerce_v2.dtos.brand.BrandUpdateDTO;
import com.fronchak.e_commerce_v2.entities.Brand;
import com.fronchak.e_commerce_v2.exceptions.ResourceNotFoundException;
import com.fronchak.e_commerce_v2.mappers.BrandMapper;
import com.fronchak.e_commerce_v2.repositories.BrandRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BrandService {

	@Autowired
	private BrandRepository repository;
	
	@Autowired
	private BrandMapper mapper;
	
	@Transactional
	public BrandOutputDTO save(BrandInsertDTO inputDTO) {
		Brand entity = new Brand();
		mapper.copyBrandInputDTOToBrand(inputDTO, entity);
		entity = repository.save(entity);
		return mapper.convertBrandToBrandOutputDTO(entity);
	}
	
	@Transactional
	public BrandOutputDTO update(BrandUpdateDTO inputDTO, Long id) {
		try {
			Brand entity = repository.getReferenceById(id);
			mapper.copyBrandInputDTOToBrand(inputDTO, entity);
			entity = repository.save(entity);
			return mapper.convertBrandToBrandOutputDTO(entity);	
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Brand", id.toString());
		}
	}
	
	@Transactional(readOnly = true)
	public BrandOutputDTO findById(Long id) {
		Brand entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Brand", id.toString()));
		return mapper.convertBrandToBrandOutputDTO(entity);
	}
	
	public List<BrandOutputDTO> findAll() {
		return null;
	}
	
	public void delete(Long id) {
		
	}
}
