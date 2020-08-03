package com.jbank.olb.customerProfile.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jbank.olb.common.model.account.AccountType;
import com.jbank.olb.common.model.account.IAccount;
import com.jbank.olb.customerProfile.model.Article;
import com.jbank.olb.customerProfile.model.account.BankAccount;
import com.jbank.olb.customerProfile.model.account.CreditCardAccount;

@Service
public class AccountServiceDummyImpl implements AccountService {
	private static final Logger logger = LoggerFactory.getLogger(AccountServiceDummyImpl.class);
	
	private List<IAccount> accounts = new ArrayList<IAccount>();
	
	public AccountServiceDummyImpl() {
		this.accounts.add(new BankAccount(AccountType.Chequing, "20153712112", new BigDecimal(1111)));
		this.accounts.add(new BankAccount(AccountType.Saving, "20153712146", new BigDecimal(2222)));
		
		this.accounts.add(new CreditCardAccount(AccountType.CreditCard, "2019111122223333", new BigDecimal(3456)));
	}

    @Override
    public List<IAccount> getAccounts() {
    	if (logger.isDebugEnabled()) logger.debug("get accounts");
    	
        return this.accounts;
    }

}
