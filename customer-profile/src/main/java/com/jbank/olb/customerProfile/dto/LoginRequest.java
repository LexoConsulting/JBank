package com.jbank.olb.customerProfile.dto;

public class LoginRequest {
	private String cardNo;
	private String password;
	
	@Override
	public String toString() {
		return "LoginRequest [cardNo=" + cardNo + ", password=" + password + "]";
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
