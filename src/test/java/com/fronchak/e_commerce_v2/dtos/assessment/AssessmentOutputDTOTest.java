package com.fronchak.e_commerce_v2.dtos.assessment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.e_commerce_v2.entities.Assessment;
import com.fronchak.e_commerce_v2.factories.AssessmentMocks;
import com.fronchak.e_commerce_v2util.AssessmentAsserts;

@ExtendWith(SpringExtension.class)
public class AssessmentOutputDTOTest {

	@Test
	public void constructorWithEntityShouldCreateCorrectlyObject() {
		Assessment entity = AssessmentMocks.mockAssessment();
		Instant instant = Instant.now();
		entity.setInstant(instant);
		
		AssessmentOutputDTO result = new AssessmentOutputDTO(entity);
		
		AssessmentAsserts.assertAssessmentOutputDTO_0(result);
		assertEquals(instant, result.getInstant());
	}
}
