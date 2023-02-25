package com.fronchak.e_commerce_v2.dtos.brand;

import java.io.Serializable;

public class BrandInputDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String imgUrl;
	
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
