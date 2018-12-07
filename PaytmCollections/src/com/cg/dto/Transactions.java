package com.cg.dto;


public class Transactions
{
	String transactionType;
	Double amount;

	public Transactions() {
		super();
	}
	@Override
	public String toString() {
		return "Transactions [transactionType=" + transactionType + ", amount=" + amount + "]";
	}

	public Transactions(String transactionType, Double amount) {
		super();
		this.transactionType = transactionType;
		this.amount = amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
