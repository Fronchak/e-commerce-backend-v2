package com.fronchak.e_commerce_v2.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.fronchak.e_commerce_v2.dtos.assessment.AssessmentOutputDTO;

public class AssessmentAsserts {

	public static void assertAssessmentOutputDTO_0(AssessmentOutputDTO result) {
		assertEquals(40L, result.getUser().getId());
		assertEquals("Mock assessment message 0", result.getMessage());
		assertEquals(1, result.getGrade());
	}
	
	public static void assertAssessmentOutputDTO_1(AssessmentOutputDTO result) {
		assertEquals(41L, result.getUser().getId());
		assertEquals("Mock assessment message 1", result.getMessage());
		assertEquals(2, result.getGrade());
	}
	
	public static void assestAssessmentOutputDTOs(List<AssessmentOutputDTO> result) {
		assertAssessmentOutputDTO_0(result.get(0));
		assertAssessmentOutputDTO_1(result.get(1));
	}
}
