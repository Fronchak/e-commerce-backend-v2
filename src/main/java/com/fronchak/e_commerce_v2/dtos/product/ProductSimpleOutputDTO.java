package com.fronchak.e_commerce_v2.dtos.product;

import java.io.Serializable;

import com.fronchak.e_commerce_v2.entities.Product;

public class ProductSimpleOutputDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String imgUrl;
	private Double price;
	private Integer grade;
	private Integer numberOfAssessments;
	
	public ProductSimpleOutputDTO() {}
	
	public ProductSimpleOutputDTO(Product entity) {
		id = entity.getId();
		name = entity.getName();
		imgUrl = entity.getImgUrl();
		price = entity.getPrice();
		grade = entity.averageGrade().getAvaliation();
		numberOfAssessments = entity.numberOfAssessments();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getNumberOfAssessments() {
		return numberOfAssessments;
	}

	public void setNumberOfAssessments(Integer numberOfAssessments) {
		this.numberOfAssessments = numberOfAssessments;
	}
}
