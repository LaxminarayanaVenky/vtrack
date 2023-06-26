package com.vensai.vtrack.authorisation.dto;

import org.springframework.stereotype.Component;

@Component
public class ResetPassword {
	private String token;
	private String newPassword;
	
	@Override
	public String toString() {
		return "ResetPassword [token=" + token + ", newPassword=" + newPassword + "]";
	}
	public ResetPassword(String token, String newPassword) {
		super();
		this.token = token;
		this.newPassword = newPassword;
	}
	
	public ResetPassword() {
		super();
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	

}
