package com.jbank.olb.common.model.customer;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ICustomer {
	String getCardNo();
	CardType getCardType();
	
	default boolean isRetail() {
		return CardType.FBC.equals(this.getCardType()) || CardType.RMC.equals(this.getCardType());
	}
	
	default boolean isDebitCard() {
		return CardType.FBC.equals(this.getCardType()) || CardType.FBCB.equals(this.getCardType());
	}
	
	default boolean isDebitMasterCard() {
		return true;
	}
	
	String getFirstName();
	String getMiddleName();
	String getLastName();
	default String getName() {
		if (this.getMiddleName() == null || this.getMiddleName().isEmpty()) {
			return this.getFirstName() + " " + this.getLastName();
		} else {
			return this.getFirstName() + " " + this.getMiddleName() + " " + this.getLastName();
		}
	}
	
	String getEmail();
	
	String getOcifId();
	String getEcifId();
	
	String getPreferredLanguage();	// Lower case: en or fr
	
	LocalDateTime getLastLogin();
}
