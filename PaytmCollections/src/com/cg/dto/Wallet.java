package com.cg.dto;


public class Wallet 
{
	private Double balance;

	
	public Wallet(Double amount) {
		this.balance=amount;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return ", balance="+balance;
	}
	public Wallet() {
		super();
	}

}
