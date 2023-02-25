package com.fronchak.e_commerce_v2.entities.pks;

import java.util.Objects;

import com.fronchak.e_commerce_v2.entities.Product;
import com.fronchak.e_commerce_v2.entities.User;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class AssessmentPK {

	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "id_product")
	private Product product;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		return Objects.hash(product, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssessmentPK other = (AssessmentPK) obj;
		return Objects.equals(product, other.product) && Objects.equals(user, other.user);
	}
}
