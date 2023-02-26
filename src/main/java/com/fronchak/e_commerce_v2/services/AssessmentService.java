package com.fronchak.e_commerce_v2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fronchak.e_commerce_v2.dtos.assessment.AssessmentInputDTO;
import com.fronchak.e_commerce_v2.dtos.assessment.AssessmentOutputDTO;
import com.fronchak.e_commerce_v2.entities.Assessment;
import com.fronchak.e_commerce_v2.exceptions.ResourceNotFoundException;
import com.fronchak.e_commerce_v2.mappers.AssessmentMapper;
import com.fronchak.e_commerce_v2.repositories.AssessmentRepository;
import com.fronchak.e_commerce_v2.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service	
public class AssessmentService {

	@Autowired
	private AssessmentRepository assessmentRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private AssessmentMapper mapper;
	
	@Autowired
	private AuthService authService;
	
	@Transactional
	public AssessmentOutputDTO save(AssessmentInputDTO inputDTO) {
		try {
			Assessment entity = new Assessment();
			mapper.copyAssessmentInputDTOToAssessment(inputDTO, entity);
			entity.setProduct(productRepository.getReferenceById(inputDTO.getIdProduct()));
			entity.setUser(authService.authenticated());
			entity = assessmentRepository.save(entity);
			return mapper.convertAssessmentToAssessmentOutputDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Product", inputDTO.getIdProduct().toString());
		}
	}
	
	@Transactional
	public AssessmentOutputDTO update(AssessmentInputDTO inputDTO, Long id) {
		
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<AssessmentOutputDTO> findAllByProduct(Long idProduct) {
		
		return null;
	}
	
	public void deleteById(Long id) {
		
	}
}
