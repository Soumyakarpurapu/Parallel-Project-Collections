package com.cg.ui;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import com.cg.dto.Customer;
import com.cg.dto.Wallet;
import com.cg.dto.Transactions;
import com.cg.exception.WalletBalanceException;
import com.cg.exception.InvalidInputException;
import com.cg.service.IPaytmWalletService;
import com.cg.service.PaytmWalletServiceImpl;

public class User 
{
	IPaytmWalletService service;

	User() {

		service = new PaytmWalletServiceImpl();
	}

	public void menu() {
		System.out.println("Welcome to Paytm App");
		System.out.println("1) Create Account");
		System.out.println("2) Show Balance");
		System.out.println("3) Deposit Amount");
		System.out.println("4) Withdraw Amount");
		System.out.println("5) Transfer Amount");
		System.out.println("6) Print Transactions");
		System.out.println("0) Exit Application");

		Scanner sc = new Scanner(System.in);

		System.out.println("Dear user,Enter your choice");
		int choice = sc.nextInt();

		switch(choice) {

		case 1:

			Customer customer = new Customer();
			Wallet wallet = new Wallet();
			System.out.println("Enter Registration details");
			System.out.print("Enter User Name: ");
			String name = sc.next();

			System.out.print("Enter Mobile Number: ");
			String mobileNumber = sc.next();

			System.out.print("Enter Amount to Initialize your Account");
			double amount = sc.nextDouble();

			try {
				customer = service.createAccount(name, mobileNumber, amount);
				System.out.println("Your account has been Registered Successfully");
			} 
			catch (InvalidInputException e) {
				e.printStackTrace();
			}
			break;

		case 2:

			System.out.println("\nEnter your mobile number");
			mobileNumber = sc.next();

			try {
				customer = service.showBalance(mobileNumber);
				System.out.print("Balance in your account is" + customer.getName());
				System.out.println(" is " + customer.getWallet().getBalance());
			} catch (InvalidInputException e3) {
				e3.printStackTrace();
			}	
			break;
		case 3:
			System.out.println("\nEnter your mobile number");
			mobileNumber = sc.next();

			System.out.println("\nEnter amount to be added into your Wallet");
			amount = sc.nextDouble();

			try {
				customer = service.depositAmount(mobileNumber, amount);
				System.out.println("Amount Added Successfully");
				System.out.println("Account balance is: " + customer.getWallet().getBalance());
			} catch (InvalidInputException e2) {
				e2.printStackTrace();
			}
			break;
		case 4:
			System.out.println("Enter your mobile number");
			mobileNumber = sc.next();

			System.out.println("\nEnter amount to be withdrawn");
			amount = sc.nextDouble();

			try {
				customer = service.withdrawAmount(mobileNumber, amount);
				System.out.println("Amount Withdrawed Successfully");
				System.out.println("Account balance is: " + customer.getWallet().getBalance());
			} catch (InvalidInputException e1) {
				e1.printStackTrace();
			} catch (WalletBalanceException e) {
				e.printStackTrace();
			}
			break;
		case 5:

			System.out.print("\nEnter your Mobile Number: ");
			String sourceMobile = sc.next();

			System.out.print("\nEnter Recipient's Mobile Number: ");
			String targetMobile = sc.next();

			System.out.println("\nEnter amount to be transferred");
			amount = sc.nextDouble();

			try {
				customer = service.fundTransfer(sourceMobile, targetMobile, amount);
				System.out.println("Amount has successfully transferred from account " + customer.getName());
				System.out.println("And now your balance is " + customer.getWallet().getBalance());

			} 
			catch (InvalidInputException e) {
				e.printStackTrace();
			} catch (WalletBalanceException e) {
				e.printStackTrace();
			}
			break;
		case 6:

			System.out.println("\nEnter mobile number to view transactions");
			String mobileNo = sc.next();

			List<Transactions> list = new ArrayList<>();
			list = service.getTransactions(mobileNo);

			Iterator<Transactions> it = list.iterator();

			while(it.hasNext()) {
				Transactions transaction = it.next();
				System.out.println("Mobile No\t: " + mobileNo);
				System.out.println("Transaction Type: " + transaction.getTransactionType());
				System.out.println("Amount\t: " + transaction.getAmount());
			}
			break;
		case 0:
			System.out.println("Thank you for using our services");
			System.out.println("Good Bye");
			System.out.println("Visit again");
			System.exit(0);
		default:
			System.out.println("Please enter valid choice");
			break;
		}
	}
	public static void main(String[] args) {

		User client = new User();

		while(true) {
			client.menu();
		}
	}

}
