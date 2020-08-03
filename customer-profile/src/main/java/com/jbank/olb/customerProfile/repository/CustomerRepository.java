package com.jbank.olb.customerProfile.repository;

import com.jbank.olb.customerProfile.model.Customer;

public interface CustomerRepository {
	
    Customer findByUsername(String username);

}