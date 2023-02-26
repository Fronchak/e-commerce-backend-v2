package com.fronchak.e_commerce_v2.dtos.user;

public class UserInsertDTO extends UserInputDTO {

	private static final long serialVersionUID = 1L;
	
	private String confirmPassword;

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
