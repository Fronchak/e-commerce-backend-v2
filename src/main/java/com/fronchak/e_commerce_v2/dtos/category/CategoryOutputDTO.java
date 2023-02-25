package com.fronchak.e_commerce_v2.dtos.category;

import com.fronchak.e_commerce_v2.entities.Category;

public class CategoryOutputDTO extends CategoryNameOutputDTO {

	private static final long serialVersionUID = 1L;
	
	private String description;
	
	public CategoryOutputDTO() {
		super();
	}
	
	public CategoryOutputDTO(Category entity) {
		super(entity);
		description = entity.getDescription();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
