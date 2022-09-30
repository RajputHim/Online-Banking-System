package com.masai.dao;

import com.masai.bean.Customer;
import com.masai.exceptions.CustomerException;

public interface CustomerDao {

	public int registerCustomer(String email, String pw);

	public String createAccount(Customer customer);

	public Customer loginCustomer(String un, String pw) throws CustomerException;

	
//	public Customer loginCustomer(String un, String pw) throws CustomerException;

}
