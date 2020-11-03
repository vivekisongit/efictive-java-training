package in.conceptarchitect.banking.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import in.conceptarchitect.banking.exceptions.InsufficientBalanceException;
import in.conceptarchitect.banking.exceptions.InvalidCredentialsException;

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
		
		
		account.withdraw(1, correctPassword); //should be successful		
		
		//what do I assert on
		
		assertEquals(balance-1, account.getBalance(),0);
		
	}
	
	
	
	@Test
	public void withdraw_shouldThrowInvalidCredentialsExceptionForWrongPassword() {
		//ACT
		try {
			account.withdraw(balance,"wrong-password");  //should throw InvalidCredentialsException
			//Oh! exceptected exception didn't occur. Its a failure
			fail("Didn't throw InvalidCredentialsException");
		
		} catch(InvalidCredentialsException ex) {
			//success. do nothing
		}
		
	}
	
	
	
	
	@Test(expected = InsufficientBalanceException.class)
	public void withdraw_shouldFailForInsufficientBalance() {

		account.withdraw(balance+1, correctPassword);		
		
	}
	
	

}
