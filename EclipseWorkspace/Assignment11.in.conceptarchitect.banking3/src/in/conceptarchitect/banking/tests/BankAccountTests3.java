package in.conceptarchitect.banking.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import in.conceptarchitect.banking.BankAccount;

public class BankAccountTests3 {
	
	
	//ARRANGE
	BankAccount account;
	String name="Vivek";
	String correctPassword="password";
	double balance=20000;
	
	@Before   //this will be called before every test
	public void init() {
		System.out.println("before called");
		account=new BankAccount(name,correctPassword, balance);
	}

	
	
	
	@Test
	public void withdraw_shouldSucceedForHappyPath() {
		
		System.out.println("withdraw_shouldSucceedForHappyPath called");
		
		//ACT
		boolean result=account.withdraw(1000, "p@ss"); //should be successful
		
		assertEquals(true, result);
	}
	
	@Test
	public void withdraw_shouldFailForWrongPassword() {
		System.out.println("withdraw_shouldFailForWrongPassword called");
		
		//ACT
		boolean result=account.withdraw(5000, "wrong-password"); //should be successful
		
		//ASSERT
		assertFalse(result);
		
	}
	
	

}
