package com.cg.exception;

public class WalletBalanceException extends Exception
{
	public WalletBalanceException() {
		super();
	}

	public WalletBalanceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public WalletBalanceException(String message, Throwable cause) {
		super(message, cause);
	}

	public WalletBalanceException(String message) {
		super(message);
	}

	public WalletBalanceException(Throwable cause) {
		super(cause);
	}
	
}
