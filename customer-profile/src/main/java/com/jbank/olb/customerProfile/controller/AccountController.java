package com.jbank.olb.customerProfile.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbank.olb.common.exception.ResourceNotFoundException;
import com.jbank.olb.common.model.account.IAccount;
import com.jbank.olb.customerProfile.service.AccountService;

@RestController
@RequestMapping("/api/account")
@Validated
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	@GetMapping("account-list")
	public List<IAccount> getAccounts(HttpServletRequest request) {
		List<IAccount> accounts = this.accountService.getAccounts();
		
		return accounts;
	}

}
