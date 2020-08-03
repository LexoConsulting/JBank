package com.jbank.olb.customerProfile.service;

import static java.util.Collections.emptyList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jbank.olb.common.model.customer.CardType;
import com.jbank.olb.customerProfile.model.Customer;
import com.jbank.olb.customerProfile.repository.CustomerRepository;
import com.jbank.olb.customerProfile.security.LoginRequest;

@Service
public class CustomerProfileService implements UserDetailsService {
    // private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
    
	private List<Customer> customers = new ArrayList<Customer>();
	
    public CustomerProfileService() {
        // this.customerRepository = customerRepository;
    }
    
    @PostConstruct
    private void init() {
        // Hard Code a customer for test
    	String cardNo = "1234";
    	String encodedPwd = bCryptPasswordEncoder.encode("123456");
    	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    	
		Customer customer = new Customer(cardNo, encodedPwd, authorities);
		
		customer.setCardNo("1234");
		customer.setCardType(CardType.FBC);
		
		customer.setFirstName("Mike");
		customer.setLastName("Yuan");
		customer.setEmail("mikeyuan2008@gmail.com");
		
		customer.setLastLogin(LocalDateTime.now());
		
		
		this.customers.add(customer);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        ApplicationUser applicationUser = customerRepository.findByUsername(username);
//        if (applicationUser == null) {
//            throw new UsernameNotFoundException(username);
//        }
    	
    	// Find the customer
		Optional<Customer> customerOpt = this.customers.stream().filter(x -> x.getCardNo().equals(username)).findFirst();
		if (!customerOpt.isPresent()) {
			throw new UsernameNotFoundException(username);
		}

		Customer customer = customerOpt.get();
		
		// Note: the encoded password returned will be reset/clean up. so we need to clone it
		customer = customer.clone();
		
        return customer;
    }
}