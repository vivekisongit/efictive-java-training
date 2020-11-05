package in.conceptarchitect.banking.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import in.conceptarchitect.banking.exceptions.InsufficientBalanceException;
import in.conceptarchitect.banking.exceptions.InvalidCredentialsException;
import in.conceptarchitect.banking.exceptions.InvalidDenominationException;



public class CurrentAccountTests {
	String password="pass";
	CurrentAccount account;
	double balance=10000;
	
	@Before
	public void setup() {
		account=new CurrentAccount("some name",password, balance);
	}
	

	@Test
	public void creditInterest_doesntCreditInterestToCurrentAccount() {

		account.creditInterest(12);
		
		assertEquals(balance, account.getBalance(),0);  //balance shouldn't change after crediting interest 
				
	}

	
	@Test
	public void withdraw_canWithdrawUptoBalanceUsingRightPassword() {
		account.withdraw(balance, password);
		assertEquals(0, account.getBalance(),0);
	}
	
	@Test(expected=InsufficientBalanceException.class)
	public void withdraw_failsForInsufficientBalance() {
		account.withdraw(balance+1, password);
	}
	
	@Test(expected=InvalidCredentialsException.class)
	public void withdraw_failsForWrongPassword() {
		account.withdraw(1, "wrong-password");
	}
	
	@Test(expected=InvalidDenominationException.class)
	public void withdraw_failsForNegativeAmount() {
		account.withdraw(-1, password);
	}
}
