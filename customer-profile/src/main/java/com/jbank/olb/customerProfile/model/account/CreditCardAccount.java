package com.jbank.olb.customerProfile.model.account;

import java.math.BigDecimal;

import com.jbank.olb.common.model.account.AccountGroupType;
import com.jbank.olb.common.model.account.AccountType;

public class CreditCardAccount extends AbstractAccount {

	public CreditCardAccount() {
		this.accountGroupType = AccountGroupType.CreditCard;
	}
	
	public CreditCardAccount(AccountType accountType, String accountNo, BigDecimal balance) {
		super(accountType, AccountGroupType.CreditCard, accountNo, accountNo, "", balance);
		
	}
}
