package com.jbank.olb.customerProfile.security;

public class LoginRequest {
	private String cardNo;
    private String password;
    
    public String getUsername() {
    	return this.cardNo;
    }
    
	@Override
	public String toString() {
		return "ApplicationUser [cardNo=" + cardNo + ", password=" + password + "]";
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
