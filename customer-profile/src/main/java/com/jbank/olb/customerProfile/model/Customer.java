package com.jbank.olb.customerProfile.model;

import java.time.LocalDateTime;
import java.util.Collection;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.jbank.olb.common.model.customer.CardType;
import com.jbank.olb.common.model.customer.ICustomer;

public class Customer extends User implements ICustomer {
	private static final long serialVersionUID = 1L;
	
	private String cardNo;
	private CardType cardType;
	
	private String firstName;
	private String middleName;
	private String lastName;
	
	private String email;
	
	private String ocifId;
	private String ecifId;
	
	private String preferredLanguage;

	private LocalDateTime lastLogin;
	
	private String token;
	
	public Customer(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	@Override
	public Customer clone() {
		Customer deepCopy = (Customer) SerializationUtils.clone(this);

	    return deepCopy;
	}

	@Override
	public String toString() {
		return "Customer [cardNo=" + cardNo + ", cardType=" + cardType + ", firstName=" + firstName + ", middleName="
				+ middleName + ", lastName=" + lastName + ", email=" + email + ", ocifId=" + ocifId + ", ecifId="
				+ ecifId + ", preferredLanguage=" + preferredLanguage + ", lastLogin=" + lastLogin + ", token=" + token
				+ "]";
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType customerType) {
		this.cardType = customerType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOcifId() {
		return ocifId;
	}

	public void setOcifId(String ocifId) {
		this.ocifId = ocifId;
	}

	public String getEcifId() {
		return ecifId;
	}

	public void setEcifId(String ecifId) {
		this.ecifId = ecifId;
	}

	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLoginTime) {
		this.lastLogin = lastLoginTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
