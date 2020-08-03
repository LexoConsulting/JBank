package com.jbank.olb.customerProfile.model.account;

import java.math.BigDecimal;

import com.jbank.olb.common.model.account.AccountGroupType;
import com.jbank.olb.common.model.account.AccountType;
import com.jbank.olb.common.model.account.IAccount;

public abstract class AbstractAccount implements IAccount {
	protected AccountType accountType;
	protected AccountGroupType accountGroupType;
	
	protected String accountNo;
	protected String accountMaskNo;
	
	protected String name;
	
	protected BigDecimal balance;
	
	public AbstractAccount() {
	}
	
	public AbstractAccount(AccountType accountType, AccountGroupType accountGroupType, String accountNo,
			String accountMaskNo, String name, BigDecimal balance) {
		super();
		this.accountType = accountType;
		this.accountGroupType = accountGroupType;
		this.accountNo = accountNo;
		this.accountMaskNo = accountMaskNo;
		this.name = name;
		this.balance = balance;
	}



	@Override
	public String toString() {
		return "AbstractAccount [accountType=" + accountType + ", accountGroupType=" + accountGroupType + ", accountNo="
				+ accountNo + ", accountMaskNo=" + accountMaskNo + ", name=" + name + ", balance=" + balance + "]";
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public AccountGroupType getAccountGroupType() {
		return accountGroupType;
	}

	public void setAccountGroupType(AccountGroupType accountGroupType) {
		this.accountGroupType = accountGroupType;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountMaskNo() {
		return accountMaskNo;
	}

	public void setAccountMaskNo(String accountMaskNo) {
		this.accountMaskNo = accountMaskNo;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
