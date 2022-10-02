package com.masai.dao;

import java.util.List;

import com.masai.bean.Customer;
import com.masai.bean.TransactionHistory;
import com.masai.exceptions.CustomerException;

public interface CustomerDao {

	public int registerCustomer(String email, String pw, int id);

	public String createAccount(Customer customer);

	public Customer loginCustomer(String un, String pw) throws CustomerException;

	public String deductMoney(int account_num, int amount) throws CustomerException;

	public String transferMoney(int account_num, int amount, Customer customer) throws CustomerException;

	// Edit account detail methods
	public String updateName(int account_num, String fName, String lName) throws CustomerException;

	public String updateMobileNumber(int account_num, int mob) throws CustomerException;

	public String updateEmail(int account_num, String email) throws CustomerException;

	public String updatePassword(int account_num, String pw) throws CustomerException;

	public String removeAccount(int account_num) throws CustomerException;

	public Customer viewAccount(int account_num) throws CustomerException;

	public List<Customer> viewAllAccount() throws CustomerException;

	public List<TransactionHistory> transactionHistory(int cid) throws CustomerException;

}
