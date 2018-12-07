package com.cg.dao;
import com.cg.dto.Customer;

public interface IPaytmWalletDao
{
	public boolean save(Customer customer);
	public Customer findOne(String mobileNo);
	public void remove(String mobileNo);
}
