package com.fronchak.e_commerce_v2.dtos.assessment;

import java.io.Serializable;

import com.fronchak.e_commerce_v2.dtos.user.UserOutputDTO;
import com.fronchak.e_commerce_v2.entities.Assessment;

public class AssessmentOutputDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UserOutputDTO user;
	private String message;
	private Integer grade;
	
	public AssessmentOutputDTO() {}
	
	public AssessmentOutputDTO(Assessment entity) {
		user = new UserOutputDTO(entity.getUser());
		message = entity.getMessage();
		grade = entity.getGrade().getAvaliation();
	}
	
	public UserOutputDTO getUser() {
		return user;
	}
	
	public void setUser(UserOutputDTO user) {
		this.user = user;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Integer getGrade() {
		return grade;
	}
	
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
}
