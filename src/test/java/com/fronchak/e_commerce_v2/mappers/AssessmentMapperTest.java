package com.fronchak.e_commerce_v2.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.e_commerce_v2.dtos.assessment.AssessmentInputDTO;
import com.fronchak.e_commerce_v2.dtos.assessment.AssessmentOutputDTO;
import com.fronchak.e_commerce_v2.entities.Assessment;
import com.fronchak.e_commerce_v2.entities.enums.Grade;
import com.fronchak.e_commerce_v2.factories.AssessmentMocks;
import com.fronchak.e_commerce_v2util.AssessmentAsserts;

@ExtendWith(SpringExtension.class)
public class AssessmentMapperTest {

	private AssessmentMapper mapper;
	
	@BeforeEach
	void setUp() {
		mapper = new AssessmentMapper();
	}
	
	@Test
	public void convertAssessmentToAssessmentOutputDTOShouldConvertCorrectly() {
		Assessment entity = AssessmentMocks.mockAssessment();
		Instant instant = Instant.now();
		entity.setInstant(instant);
		
		AssessmentOutputDTO result = mapper.convertAssessmentToAssessmentOutputDTO(entity);
		
		AssessmentAsserts.assertAssessmentOutputDTO_0(result);
		assertEquals(instant, result.getInstant());
	}
	
	@Test
	public void convertAssessmentsToAssessmentOutputDTOsShouldConvertCorrectly() {
		List<Assessment> entities = AssessmentMocks.mockAssessments();
		
		List<AssessmentOutputDTO> result = mapper.convertAssessmentsToAssessmentOutputDTOs(entities);
		
		AssessmentAsserts.assestAssessmentOutputDTOs(result);
	}
	
	@Test
	public void copyAssessmentInputDTOToAssessmentShouldCopyValuesCorrectly() {
		AssessmentInputDTO inputDTO = AssessmentMocks.mockAssessmentInputDTO();
		Instant instant = Instant.now();
		inputDTO.setInstant(instant);
		Assessment entity = new Assessment();
		
		mapper.copyAssessmentInputDTOToAssessment(inputDTO, entity);
		
		assertEquals("Mock assessment message 0", entity.getMessage());
		assertEquals(Grade.HORRIBLE, entity.getGrade());
		assertEquals(instant, entity.getInstant());
	}
}
