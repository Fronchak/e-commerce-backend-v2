package com.fronchak.e_commerce_v2.exceptions;

public class NotEnoughInStockException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NotEnoughInStockException(String msg) {
		super(msg);
	}
}
