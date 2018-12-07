package com.cg.junit;

import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.service.IPaytmWalletService;
import com.cg.service.PaytmWalletServiceImpl;
import com.cg.exception.InvalidInputException;
import com.cg.exception.WalletBalanceException;
import com.cg.dto.Customer;
import com.cg.dto.Wallet;
import com.cg.dto.Transactions;

public class TestClass 
{
	static IPaytmWalletService service;
	
@BeforeClass
public static void setUpBeforeClass() throws Exception {
	service = new PaytmWalletServiceImpl();
	
}

@AfterClass
public static void tearDownAfterClass() throws Exception {
}

@Before
public void setUp() throws Exception 
{
}

@After
public void tearDown() throws Exception {
}

@Test(expected=InvalidInputException.class)
public void testCreateAccount1() throws InvalidInputException 
{
	service.createAccount(null, "9949105993", new Double(2000));
}


@Test(expected=InvalidInputException.class)
public void testCreateAccount2() throws InvalidInputException 
{
	service.createAccount("", "9949105993", new Double(2000));
}


@Test(expected=InvalidInputException.class)
public void testCreateAccount3() throws InvalidInputException 
{
	service.createAccount("soumya", "206", new Double(1500));
}


@Test(expected=InvalidInputException.class)
public void testCreateAccount4() throws InvalidInputException 
{
	service.createAccount("soumya", "", new Double(1500));
}


@Test(expected=InvalidInputException.class)
public void testCreateAccount5() throws InvalidInputException 
{
	service.createAccount("", "", new Double(1500));
}

@Test(expected=InvalidInputException.class)
public void testCreateAccount6() throws InvalidInputException 
{
	Customer expected = service.createAccount("soumya", "9949105993", new Double(500000));
	//Customer actual = null;
	
	//assertNotSame(expected, actual);
}

@Test(expected=InvalidInputException.class)
public void testCreateAccount7() throws InvalidInputException 
{
	service.createAccount("soumya", "9949105993", new Double(9000));
	service.createAccount("soni", "9949105993", new Double(10000));
}


@Test(expected=InvalidInputException.class)
public void testCreateAccount8() throws InvalidInputException 
{
	service.createAccount("soumya", "9949105993", new Double(-100));
}

@Test(expected=InvalidInputException.class)
public void testCreateAccount9() throws InvalidInputException 
{
	Customer actual=service.createAccount("soumya", null, new Double(5000.75));
}

@Test(expected=InvalidInputException.class)
public void testCreateAccount10() throws InvalidInputException 
{
	Customer actual=service.createAccount("divya", "9988776655", new Double(5000.75));
	
}



@Test(expected=InvalidInputException.class)
public void testShowBalance11() throws InvalidInputException 
{
	service.showBalance(null);		
}


@Test(expected=InvalidInputException.class)
public void testShowBalance12() throws InvalidInputException 
{
	service.showBalance("");		
}


@Test(expected=InvalidInputException.class)
public void testShowBalance13() throws InvalidInputException 
{
	service.showBalance("12345");		
}


@Test(expected=InvalidInputException.class)
public void testShowBalance14() throws InvalidInputException 
{
	service.showBalance("8519993220");		
}


@Test(expected=InvalidInputException.class)
public void testShowBalance15() throws InvalidInputException 
{
	service.showBalance("8519993220");		
}


@Test
public void testShowBalance16() throws InvalidInputException 
{
	service.createAccount("Soumya", "7075967022", new Double(7000));
	Customer customer=service.showBalance("7075967022");
	Double expectedResult=new Double(7000);
	Double obtainedResult=customer.getWallet().getBalance();
	
	assertEquals(expectedResult, obtainedResult);
	
}

@Test(expected=InvalidInputException.class)
public void testWithdrawAmount() throws InvalidInputException, WalletBalanceException {
	String name = "Soumya";
	String mobileNumber = "7075967022";
	Double balance = new Double(7000);
	
	service.createAccount(name, mobileNumber, balance);
	
	Double amount = new Double(0);
	
	Customer customer = service.withdrawAmount(mobileNumber, amount);
	assertEquals(new Double(0), customer.getWallet().getBalance());
}

@Test(expected = Exception.class)
public void testWithdrawAmount1() throws InvalidInputException, WalletBalanceException {
	String name = "Soumya";
	String mobileNumber = "7075967022";
	Double balance = new Double(2000);
	
	service.createAccount(name, mobileNumber, balance);
	
	Double amount = new Double(3000);
	
	Customer customer=service.withdrawAmount(mobileNumber, amount);
	
}

@Test(expected=InvalidInputException.class)
public void testDepositAmount() throws InvalidInputException {
	String name = "soumya";
	String mobileNumber = "7075967022";
	Double balance = new Double(3000);
	
	Customer customer = service.createAccount(name, mobileNumber, balance);
	
	Customer customer1 = service.depositAmount(mobileNumber, new Double(3000));
	
	assertEquals(new Double(6000), customer1.getWallet().getBalance());
}

@Test(expected=InvalidInputException.class)
public void testDepositAmount1() throws InvalidInputException {
	String name = "soumya";
	String mobileNumber = "7075967022";
	Double balance = new Double(2000);
	
	Customer customer = service.createAccount(name, mobileNumber, balance);
	
	Customer customer1 = service.depositAmount(mobileNumber, new Double(0));
	
}

@Test(expected = InvalidInputException.class)
public void testMobileNumber() throws InvalidInputException, WalletBalanceException {
	String name = "soumya";
	String mobileNumber = "994910599321";
	Double balance = new Double(3000);
	
	service.createAccount(name, mobileNumber, balance);
	
	Double amount = new Double(3000);
	
	service.withdrawAmount(mobileNumber, amount);
}
@Test(expected = InvalidInputException.class)
public void testMobileNumber1() throws InvalidInputException, WalletBalanceException {
	String name = "soumya";
	String mobileNumber = "994910599";
	Double balance = new Double(3000);
	
	service.createAccount(name, mobileNumber, balance);
	
	Double amount = new Double(3000);
	
	service.withdrawAmount(mobileNumber, amount);
}

@Test(expected = WalletBalanceException.class)
public void testFundTransfer() throws InvalidInputException, WalletBalanceException 
{
	service.createAccount("Soumya", "9949105993", new Double(1000));
	service.createAccount("Prathi", "9949323688", new Double(1000));
	service.fundTransfer("9949105993", "9949323688", new Double(2000));
}

}
