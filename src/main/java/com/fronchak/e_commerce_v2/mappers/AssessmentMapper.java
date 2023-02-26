package com.fronchak.e_commerce_v2.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fronchak.e_commerce_v2.dtos.assessment.AssessmentInputDTO;
import com.fronchak.e_commerce_v2.dtos.assessment.AssessmentOutputDTO;
import com.fronchak.e_commerce_v2.entities.Assessment;
import com.fronchak.e_commerce_v2.entities.enums.Grade;

@Service
public class AssessmentMapper {

	public AssessmentOutputDTO convertAssessmentToAssessmentOutputDTO(Assessment entity) {
		return new AssessmentOutputDTO(entity);
	}
	
	public List<AssessmentOutputDTO> convertAssessmentsToAssessmentOutputDTOs(List<Assessment> entities) {
		return entities.stream()
				.map((entity) -> convertAssessmentToAssessmentOutputDTO(entity))
				.collect(Collectors.toList());
	}
	
	public void copyAssessmentInputDTOToAssessment(AssessmentInputDTO dto, Assessment entity) {
		entity.setMessage(dto.getMessage());
		entity.setInstant(dto.getInstant());
		entity.setGrade(Grade.toEnum(dto.getGrade()));
	}
}
