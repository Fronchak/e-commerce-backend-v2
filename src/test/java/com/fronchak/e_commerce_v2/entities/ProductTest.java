package com.fronchak.e_commerce_v2.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.e_commerce_v2.entities.enums.Grade;
import com.fronchak.e_commerce_v2.factories.AssessmentMocks;

@ExtendWith(SpringExtension.class)
public class ProductTest {

	@Test
	public void productShouldBeCreateWithQuantityEqualsToZero() {
		Product result = new Product();
		
		assertEquals(0, result.getQuantity());
	}
	
	@Test
	public void inStockShouldReturnFalseIfQuantityIsZero() {
		Product result = new Product();
		result.setQuantity(0);
		
		assertFalse(result.inStock());
	}
	
	@Test
	public void inStockShouldReturnTrueIfThereIsAtLeastOneInStock() {
		Product result = new Product();
		result.setQuantity(1);
		
		assertTrue(result.inStock());
	}
	
	@Test
	public void numberOfAssessmentShouldReturnQuantityOfAssessment() {
		Product product = new Product();
		product.addAssessment(AssessmentMocks.mockAssessment(0));
		product.addAssessment(AssessmentMocks.mockAssessment(1));
		
		assertEquals(2, product.numberOfAssessments());
	}
	
	@Test
	public void averageGradeShouldReturnAverageOfAllAssessmentsOfThatProduct_test1() {
		Product product = new Product();
		Assessment assessment = AssessmentMocks.mockAssessment();
		assessment.setGrade(Grade.GOOD);
		product.addAssessment(assessment);
		
		Grade result = product.averageGrade();
		
		assertEquals(Grade.GOOD, result);
	}
	
	@Test
	public void averageGradeShouldReturnAverageOfAllAssessmentsOfThatProduct_test2() {
		Product product = new Product();
		
		Assessment assessment0 = AssessmentMocks.mockAssessment(0);
		assessment0.setGrade(Grade.GOOD);
		
		Assessment assessment1 = AssessmentMocks.mockAssessment(1);
		assessment1.setGrade(Grade.BAD);
		
		product.addAssessment(assessment0);
		product.addAssessment(assessment1);
		
		Grade result = product.averageGrade();
		
		assertEquals(Grade.AVERAGE, result);
	}
	
	@Test
	public void averageGradeShouldReturnAverageWhenThereIsAssessmentYet() {
		Product product = new Product();
		
		Grade result = product.averageGrade();
		
		assertEquals(Grade.AVERAGE, result);
	}		
}
