package com.cg.dao;
import java.util.Map;
import java.util.HashMap;
import com.cg.dto.Customer;

public class PaytmWalletDaoImpl implements IPaytmWalletDao
{
	private Map<String, Customer> data; 
	
	public PaytmWalletDaoImpl() {

		data = new HashMap<String, Customer>();
	}
	@Override
	public boolean save(Customer customer) 
	{
		if(data.get(customer.getMobileNo()) == null) {
			data.put(customer.getMobileNo(), customer);
			return true;
		}
		return false;
	}
	@Override
	public Customer findOne(String mobileNo)
	{
		if(data.get(mobileNo) != null) {
			return data.get(mobileNo);
		}
		return null;
	}	
	@Override
	public void remove(String mobileNo) 
	{
		data.remove(mobileNo);		
	}

}
