package com.fronchak.e_commerce_v2.entities;

import java.time.Instant;
import java.util.Objects;

import com.fronchak.e_commerce_v2.entities.enums.Grade;
import com.fronchak.e_commerce_v2.entities.pks.AssessmentPK;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "assessment")
public class Assessment {

	@EmbeddedId
	private AssessmentPK id = new AssessmentPK();
	
	@Column(columnDefinition = "TEXT")
	private String message;
	
	@Column(columnDefinition = "TIMESTAMP")
	private Instant instant;
	
	@Enumerated(EnumType.ORDINAL)
	private Grade grade;
	
	public Assessment() {};
	
	public Assessment(User user, Product product) {
		setId(user, product);
	}
	
	public void setId(User user, Product product) {
		id.setUser(user);
		id.setProduct(product);
	}
	
	public User getUser() {
		return id.getUser();
	}
	
	public void setUser(User user) {
		id.setUser(user);
	}

	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Instant getInstant() {
		return instant;
	}

	public void setInstant(Instant instant) {
		this.instant = instant;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
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
		Assessment other = (Assessment) obj;
		return Objects.equals(id, other.id);
	}
}
