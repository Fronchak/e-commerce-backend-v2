package com.fronchak.e_commerce_v2.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sell")
public class Sell extends Order {

	@ManyToOne
	@JoinColumn(name = "id_address")
	private Address deliveryAddress;

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
}
