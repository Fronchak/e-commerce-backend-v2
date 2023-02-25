package com.fronchak.e_commerce_v2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fronchak.e_commerce_v2.dtos.brand.BrandInsertDTO;
import com.fronchak.e_commerce_v2.dtos.brand.BrandOutputDTO;
import com.fronchak.e_commerce_v2.dtos.brand.BrandUpdateDTO;
import com.fronchak.e_commerce_v2.entities.Brand;
import com.fronchak.e_commerce_v2.exceptions.DatabaseException;
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
	
	@Transactional(readOnly = true)
	public List<BrandOutputDTO> findAll() {
		List<Brand> entities = repository.findAll();
		return mapper.convertBrandsToBrandOutputDTOs(entities);
	}
	
	public void deleteById(Long id) {
		try {
			repository.deleteById(id);	
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Brand", id.toString());
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Brand cannot be deleted");
		}
	}
}
