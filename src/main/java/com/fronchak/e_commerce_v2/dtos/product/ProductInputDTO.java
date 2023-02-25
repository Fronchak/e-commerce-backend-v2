package com.fronchak.e_commerce_v2.dtos.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductInputDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String description;
	private String imgUrl;
	private Double price;
	private Long idBrand;
	private List<Long> idCategories = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
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
	
	public Long getIdBrand() {
		return idBrand;
	}
	
	public void setIdBrand(Long idBrand) {
		this.idBrand = idBrand;
	}
	
	public List<Long> getIdCategories() {
		return idCategories;
	}
	
	public void setIdCategories(List<Long> idCategories) {
		this.idCategories = idCategories;
	}
}
