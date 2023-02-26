package com.fronchak.e_commerce_v2.dtos.user;

public class UserUpdateDTO extends UserInputDTO {

	private static final long serialVersionUID = 1L;
	
	private String newPassword;
	private String confirmNewPassword;
	
	public String getNewPassword() {
		return newPassword;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}
	
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
}
