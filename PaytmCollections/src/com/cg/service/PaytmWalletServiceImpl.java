package com.cg.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cg.dao.IPaytmWalletDao;
import com.cg.dao.PaytmWalletDaoImpl;
import com.cg.dto.Customer;
import com.cg.dto.Transactions;
import com.cg.dto.Wallet;
import com.cg.exception.WalletBalanceException;
import com.cg.exception.InvalidInputException;

public class PaytmWalletServiceImpl implements IPaytmWalletService
{
	private IPaytmWalletDao dao;
	List<Transactions> list;
	Map<String, List<Transactions>> transactions;

	public PaytmWalletServiceImpl() {

		list = new ArrayList<>();
		dao = new PaytmWalletDaoImpl();
		transactions = new HashMap<>();
	}

	@Override
	public Customer createAccount(String name, String mobileNo,Double amount) throws InvalidInputException
	{
		if(isValid(mobileNo) && isValidName(name) && amount.compareTo(new Double(0)) > 0) {

			Wallet wallet = new Wallet();
			Customer customer = new Customer();

			wallet.setBalance(amount);
			customer.setName(name);
			customer.setMobileNo(mobileNo);
			customer.setWallet(wallet);

			if(dao.save(customer) == true) {
				transactions.put(mobileNo, null);
				return customer;
			}
			else
				throw new InvalidInputException("User already present");
		}
		else throw new InvalidInputException("Enter valid details");
	}

	@Override
	public Customer showBalance(String mobileNo) throws InvalidInputException
	{
		if(isValid(mobileNo)) {

			Customer customer=dao.findOne(mobileNo);
			if(customer!=null)
				return customer;
			else
				throw new InvalidInputException("No person with this mobile no ");
		}
		else 
			throw new InvalidInputException("Enter valid mobile number");
	}

	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo,Double amount) throws InvalidInputException, WalletBalanceException
	{
		if(isValid(sourceMobileNo) == false || isValid(targetMobileNo) == false || sourceMobileNo.equals(targetMobileNo)) throw new InvalidInputException();

		Customer customer = withdrawAmount(sourceMobileNo, amount);
		depositAmount(targetMobileNo, amount);

		return customer;
	}

	@Override
	public Customer depositAmount(String mobileNo, Double amount) throws InvalidInputException
	{
		if(amount.compareTo(new Double(0)) <= 0) 
			throw new InvalidInputException();

		if(isValid(mobileNo)) {

			Customer customer = dao.findOne(mobileNo);
			Wallet wallet = customer.getWallet();
			wallet.setBalance(wallet.getBalance()+(amount));

			Transactions transaction = new Transactions();
			transaction.setAmount(amount);
			transaction.setTransactionType("Deposit");

			list.add(transaction);
			if(transactions.containsKey(mobileNo)) {
				transactions.remove(mobileNo);
				transactions.put(mobileNo, list);
			}

			dao.remove(mobileNo);

			if(dao.save(customer)) {
				return customer;
			}
		}
		return null;
	}

	@Override
	public Customer withdrawAmount(String mobileNo, Double amount) throws InvalidInputException, WalletBalanceException
	{
		if(isValid(mobileNo)) {

			Customer customer = dao.findOne(mobileNo);
			Wallet wallet = customer.getWallet();
			wallet.setBalance(wallet.getBalance()-(amount));

			if(amount.compareTo(wallet.getBalance()) > 0) 
				throw new WalletBalanceException("Amount is not sufficient in your account");

			Transactions transaction = new Transactions();
			transaction.setAmount(amount);
			transaction.setTransactionType("Withdraw");

			list.add(transaction);
			if(transactions.containsKey(mobileNo)) {
				transactions.remove(mobileNo);
				transactions.put(mobileNo, list);
			}

			dao.remove(mobileNo);

			dao.save(customer);

			return customer;
		}
		else throw new InvalidInputException("Enter valid mobile number");
	}

	@Override
	public List<Transactions> getTransactions(String mobileNo) 
	{
		return transactions.get(mobileNo);
	}

	public boolean isValid(String mobileNo) {

		if(mobileNo != null && mobileNo.matches("[1-9][0-9]{9}")) {
			return true;
		} else 
			return false;
	}

	private boolean isValidName(String name) {

		if( name != null && name.matches("[A-Z]{1}[a-z]{3,10}"))
			return true;
		else return false;
	}

}
