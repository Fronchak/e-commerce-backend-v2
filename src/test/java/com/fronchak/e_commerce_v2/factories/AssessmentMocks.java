package com.fronchak.e_commerce_v2.factories;

import java.util.ArrayList;
import java.util.List;

import com.fronchak.e_commerce_v2.dtos.assessment.AssessmentInputDTO;
import com.fronchak.e_commerce_v2.dtos.assessment.AssessmentOutputDTO;
import com.fronchak.e_commerce_v2.entities.Assessment;
import com.fronchak.e_commerce_v2.entities.Product;
import com.fronchak.e_commerce_v2.entities.enums.Grade;

public class AssessmentMocks {

	public static Assessment mockAssessment() {
		return mockAssessment(0);
	}
	
	public static Assessment mockAssessment(int i) {
		Assessment mock = new Assessment();
		mock.setUser(UserMocks.mockUser(i));
		mock.setProduct(mockProduct(i));
		mock.setMessage(mockMessage(i));
		mock.setGrade(mockGrade(i));
		return mock;
	}
	
	private static Product mockProduct(int i) {
		Product mock = new Product();
		mock.setId(i + 0L);
		return mock;
	}
	
	private static String mockMessage(int i) {
		return "Mock assessment message " + i;
	}
	
	private static Grade mockGrade(int i) {
		return Grade.toEnum(i + 1);
	}
	
	public static AssessmentOutputDTO mockAssessmentOutputDTO() {
		return mockAssessmentOutputDTO(0);
	}
	
	public static AssessmentOutputDTO mockAssessmentOutputDTO(int i) {
		AssessmentOutputDTO mock = new AssessmentOutputDTO();
		mock.setUser(UserMocks.mockUserOutputDTO(i));
		mock.setMessage(mockMessage(i));
		mock.setGrade(i + 1);
		return mock;
	}
	
	public static List<Assessment> mockAssessments() {
		List<Assessment> mocks = new ArrayList<>();
		mocks.add(mockAssessment(0));
		mocks.add(mockAssessment(1));
		return mocks;
	}
	
	public static List<AssessmentOutputDTO> mockAssessmentOutputDTOs() {
		List<AssessmentOutputDTO> mocks = new ArrayList<>();
		mocks.add(mockAssessmentOutputDTO(0));
		mocks.add(mockAssessmentOutputDTO(1));
		return mocks;
	}
	
	public static AssessmentInputDTO mockAssessmentInputDTO() {
		AssessmentInputDTO mock = new AssessmentInputDTO();
		mock.setGrade(1);
		mock.setMessage(mockMessage(0));
		mock.setIdProduct(1L);
		return mock;
	}
}
