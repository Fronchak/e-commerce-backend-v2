package com.fronchak.e_commerce_v2.exceptions;

public class InvalidEnumCodeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidEnumCodeException(String msg) {
		super(msg);
	}
}
