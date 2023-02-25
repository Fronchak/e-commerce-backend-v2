package com.fronchak.e_commerce_v2.dtos.brand;

import java.io.Serializable;

import com.fronchak.e_commerce_v2.entities.Brand;

public class BrandOutputDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String imgUrl;
	
	public BrandOutputDTO() {}
	
	public BrandOutputDTO(Brand entity) {
		id = entity.getId();
		name = entity.getName();
		imgUrl = entity.getImgUrl();
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
}
