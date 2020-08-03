package com.jbank.olb.customerProfile.model.account;

import java.math.BigDecimal;

import com.jbank.olb.common.model.account.AccountGroupType;
import com.jbank.olb.common.model.account.AccountType;

public class BankAccount extends AbstractAccount {

	public BankAccount() {
		this.accountGroupType = AccountGroupType.Banking;
	}
	
	public BankAccount(AccountType accountType, String accountNo, BigDecimal balance) {
		super(accountType, AccountGroupType.Banking, accountNo, accountNo, "", balance);
		
	}
}
