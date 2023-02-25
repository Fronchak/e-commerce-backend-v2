package com.fronchak.e_commerce_v2.exceptions;

public class DatabaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DatabaseException(String msg) {
		super(msg);
	}
	
	public static String getError() {
		return "Integrity error";
	}

}
