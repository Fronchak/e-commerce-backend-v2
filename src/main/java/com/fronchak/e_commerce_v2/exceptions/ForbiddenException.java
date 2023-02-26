package com.fronchak.e_commerce_v2.exceptions;

public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ForbiddenException(String msg) {
		super(msg);
	}
	
	public static String getError() {
		return "Forbidden";
	}

}
