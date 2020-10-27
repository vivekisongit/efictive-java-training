package in.conceptarchitect.banking.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import in.conceptarchitect.banking.BankAccount;

public class BankAccountTests {
	
	
	//ARRANGE
	BankAccount account;
	String name="Vivek";
	String correctPassword="password";
	double balance=20000;
	
	@Before   //this will be called before every test
	public void init() {
		
		account=new BankAccount(name,correctPassword, balance);
	}

	
	
	
	@Test
	public void withdraw_shouldSucceedForHappyPath() {
		
		
		//ACT
		boolean result=account.withdraw(balance, correctPassword); //should be successful		
		assertEquals(true, result);
	}
	
	@Test
	public void withdraw_shouldFailForWrongPassword() {
		//ACT
		boolean result=account.withdraw(balance, "wrong-password"); //should be successful
		
		//ASSERT
		assertFalse(result);
		
	}
	
	@Test
	public void withdraw_shouldFailForInsufficientBalance() {
		boolean result=account.withdraw(balance+1, correctPassword);
		
		assertFalse(result);
	}
	
	

}
