package com.fronchak.e_commerce_v2.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.e_commerce_v2.dtos.assessment.AssessmentInputDTO;
import com.fronchak.e_commerce_v2.dtos.assessment.AssessmentOutputDTO;
import com.fronchak.e_commerce_v2.entities.Assessment;
import com.fronchak.e_commerce_v2.entities.Product;
import com.fronchak.e_commerce_v2.entities.User;
import com.fronchak.e_commerce_v2.exceptions.ResourceNotFoundException;
import com.fronchak.e_commerce_v2.exceptions.UnauthorizedException;
import com.fronchak.e_commerce_v2.factories.AssessmentMocks;
import com.fronchak.e_commerce_v2.factories.ProductMocks;
import com.fronchak.e_commerce_v2.factories.UserMocks;
import com.fronchak.e_commerce_v2.mappers.AssessmentMapper;
import com.fronchak.e_commerce_v2.repositories.AssessmentRepository;
import com.fronchak.e_commerce_v2.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
public class AssessmentServiceTest {

	@Mock
	private AssessmentRepository assessmentRepository;
	
	@Mock
	private ProductRepository productRepository;
	
	@Mock
	private AssessmentMapper mapper;
	
	@Mock
	private AuthService authService;
	
	@InjectMocks
	private AssessmentService assessmentService;
	
	private Assessment entity;
	private AssessmentOutputDTO outputDTO;
	
	@BeforeEach
	void setUp() {
		entity = AssessmentMocks.mockAssessment();
		outputDTO = AssessmentMocks.mockAssessmentOutputDTO();
	}
	
	@Test
	public void saveShouldReturnAssessmentOutputDTOAfterSaveEntityWithUserLogged() {
		ArgumentCaptor<Assessment> argumentCaptor = ArgumentCaptor.forClass(Assessment.class);
		Assessment entityAfterSave = AssessmentMocks.mockAssessment();
		AssessmentInputDTO inputDTO = AssessmentMocks.mockAssessmentInputDTO();
		User user = UserMocks.mockUser();
		Product product = ProductMocks.mockProduct();
		
		when(authService.authenticated()).thenReturn(user);
		when(productRepository.getReferenceById(inputDTO.getIdProduct())).thenReturn(product);
		when(assessmentRepository.save(any(Assessment.class))).thenReturn(entityAfterSave);
		when(mapper.convertAssessmentToAssessmentOutputDTO(entityAfterSave)).thenReturn(outputDTO);
		
		AssessmentOutputDTO result = assessmentService.save(inputDTO);
		verify(assessmentRepository).save(argumentCaptor.capture());
		Assessment entitySaved = argumentCaptor.getValue();
		
		assertSame(outputDTO, result);
		verify(mapper, times(1)).copyAssessmentInputDTOToAssessment(inputDTO, entitySaved);
		assertSame(product, entitySaved.getProduct());
		assertSame(user, entitySaved.getUser());
	}
	
	@Test
	public void saveShouldThrowUnhauthorizedExceptionWhenThereIsNoUserLogged() {
		AssessmentInputDTO inputDTO = AssessmentMocks.mockAssessmentInputDTO();
		
		when(authService.authenticated()).thenThrow(UnauthorizedException.class);
		
		assertThrows(UnauthorizedException.class, () -> assessmentService.save(inputDTO));
		
		verify(assessmentRepository, never()).save(any());
	}
	
	@Test
	public void saveShouldThrowResourceNotFoundExceptionWhenProductDoesNotExist() {
		AssessmentInputDTO inputDTO = AssessmentMocks.mockAssessmentInputDTO();
		
		when(productRepository.getReferenceById(inputDTO.getIdProduct())).thenThrow(EntityNotFoundException.class);
		
		assertThrows(ResourceNotFoundException.class, () -> assessmentService.save(inputDTO));
		
		verify(assessmentRepository, never()).save(any());
	}
	
}
