package com.fronchak.e_commerce_v2.dtos.assessment;

import java.io.Serializable;

public class AssessmentInputDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idProduct;
	private String message;
	private Integer grade;
	
	public Long getIdProduct() {
		return idProduct;
	}
	
	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
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
