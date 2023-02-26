package com.fronchak.e_commerce_v2.dtos.assessment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.e_commerce_v2.entities.Assessment;
import com.fronchak.e_commerce_v2.factories.AssessmentMocks;
import com.fronchak.e_commerce_v2.services.AssessmentAsserts;

@ExtendWith(SpringExtension.class)
public class AssessmentOutputDTOTest {

	@Test
	public void constructorWithEntityShouldCreateCorrectlyObject() {
		Assessment entity = AssessmentMocks.mockAssessment();
		
		AssessmentOutputDTO result = new AssessmentOutputDTO(entity);
		
		AssessmentAsserts.assertAssessmentOutputDTO_0(result);
	}
}
