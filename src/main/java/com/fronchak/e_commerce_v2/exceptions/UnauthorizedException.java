package com.fronchak.e_commerce_v2.exceptions;

public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UnauthorizedException(String msg) {
		super(msg);
	}
	
	public static String getError() {
		return "Unauthorized";
	}

}
