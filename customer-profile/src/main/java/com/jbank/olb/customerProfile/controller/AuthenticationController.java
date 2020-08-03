package com.jbank.olb.customerProfile.controller;

import java.security.Key;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jbank.olb.common.exception.ResourceNotFoundException;
import com.jbank.olb.common.model.customer.CardType;
import com.jbank.olb.common.model.customer.ICustomer;
import com.jbank.olb.customerProfile.dto.LoginRequest;
import com.jbank.olb.customerProfile.model.Customer;

@RestController
public class AuthenticationController {
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
	private List<Customer> customers = new ArrayList<Customer>();
	
	public AuthenticationController() {
		Customer customer = new Customer("11", "", new ArrayList<GrantedAuthority>());
		
		customer.setCardNo("1234");
		customer.setCardType(CardType.FBC);
		
		customer.setFirstName("Mike");
		customer.setLastName("Yuan");
		customer.setEmail("mikeyuan2008@gmail.com");
		
		customer.setLastLogin(LocalDateTime.now());
		
		
		this.customers.add(customer);
	}

	/**
	 * Login with CardNo and Password
	 * $ curl --data "cardNo=123456&pwd=P123" http://localhost:8080/login
	 * @param name
	 * @return
	 */
	@PostMapping("/api/customer/login")
	public ResponseEntity<ICustomer> login(@RequestBody LoginRequest request) {
		Optional<Customer> customerValue = this.customers.stream().filter(x -> x.getCardNo().equals(request.getCardNo())).findFirst();

		if (customerValue.isPresent()) {
			logger.info("Login succssful for " + request.getCardNo());
			
			Customer customer = customerValue.get();
			
			String token = "fake token"; // this.generateJWTToken(customer);
			customer.setToken(token);
			
			return ResponseEntity.ok(customer);
		}
		
		throw new ResourceNotFoundException("Customer with cardNo: " + request.getCardNo() + " not found");
	}
//	
//	private String generateJWTToken(ICustomer customer) {
//		JwtUserDTO user = new JwtUserDTO();
//		user.setUserId(1L);
//		user.setUserName(customer.getCardNo());
//		
//		user.setFirstName(customer.getFirstName());
//		user.setMiddleName(customer.getMiddleName());
//		user.setLastName(customer.getLastName());
//		
//		user.setEmail(customer.getEmail());
//
//		String token = this.jwtTokenUtils.generateToken(user);
//		
//		if (logger.isDebugEnabled()) logger.debug("Token generated: " + token);
//		return token;
//	}
}
