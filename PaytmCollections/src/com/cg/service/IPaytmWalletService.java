package com.cg.service;
import java.math.BigDecimal;
import java.util.List;

import com.cg.dto.Customer;
import com.cg.dto.Transactions;
import com.cg.exception.WalletBalanceException;
import com.cg.exception.InvalidInputException;

public interface IPaytmWalletService 
{
	public Customer createAccount(String name ,String mobileno, Double amount) throws InvalidInputException;
	public Customer showBalance (String mobileno) throws InvalidInputException;
	public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, Double amount) throws InvalidInputException, WalletBalanceException;
	public Customer depositAmount (String mobileNo,Double amount ) throws InvalidInputException;
	public Customer withdrawAmount(String mobileNo, Double amount) throws InvalidInputException, WalletBalanceException;
	public List<Transactions> getTransactions(String mobileNo);
}
