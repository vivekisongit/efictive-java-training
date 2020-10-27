package in.conceptarchitect.banking.tests;

import static org.junit.Assert.*;
import org.junit.Test;

import in.conceptarchitect.banking.BankAccount;

public class BankAccountTests2 {

	@Test
	public void withdraw_shouldSucceedForHappyPath() {
		
		//ARRANGE
		BankAccount account=new BankAccount("Vivek", "p@ss", 20000);
		
		//ACT
		boolean result=account.withdraw(1000, "p@ss"); //should be successful
		
		assertEquals(true, result);
	}
	
	@Test
	public void withdraw_shouldFailForWrongPassword() {
		//ARRANGE
		BankAccount account=new BankAccount("Vivek", "p@ss", 20000);
		
		//ACT
		boolean result=account.withdraw(5000, "wrong-password"); //should be successful
		
		//ASSERT
		assertFalse(result);
		
	}
	
	

}
