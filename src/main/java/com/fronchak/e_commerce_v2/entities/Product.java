package com.fronchak.e_commerce_v2.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fronchak.e_commerce_v2.entities.enums.Grade;
import com.fronchak.e_commerce_v2.util.Convertions;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Column(columnDefinition = "TEXT", name = "img_url")
	private String imgUrl;
	
	private Double price;
	private Integer quantity;
	
	@ManyToMany
	@JoinTable(name = "product_category",
			joinColumns = @JoinColumn(name = "id_product"),
			inverseJoinColumns = @JoinColumn(name = "id_category"))
	private Set<Category> categories = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "id_brand")
	private Brand brand;
	
	@OneToMany(mappedBy = "id.product", fetch = FetchType.EAGER)
	private List<Assessment> assessments = new ArrayList<>();
	
	public Product() {
		quantity = 0;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void addCategory(Category category) {
		categories.add(category);
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	public List<Assessment> getAssessments() {
		return assessments;
	}

	public void addAssessment(Assessment assessment) {
		assessments.add(assessment);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}
	
	public boolean inStock() {
		return quantity > 0;
	}
	
	public int numberOfAssessments() {
		return assessments.size();
	}
	
	public Grade averageGrade() {
		Double average = assessments.stream()
				.mapToInt((assessment) -> assessment.getGrade().getAvaliation())
				.average().orElse(2.5);
		return Convertions.getGrade(average);
				
	}
}
