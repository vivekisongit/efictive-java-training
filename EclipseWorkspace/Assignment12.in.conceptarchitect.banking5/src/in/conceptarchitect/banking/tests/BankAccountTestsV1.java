package in.conceptarchitect.banking.tests;

import static org.junit.Assert.*;
import org.junit.Test;

import in.conceptarchitect.banking.BankAccount;

public class BankAccountTestsV1 {

	@Test
	public void test() {
		BankAccount account=new BankAccount("Vivek", "p@ss", 20000);
		
		boolean result=account.withdraw(1000, "p@ss"); //should be successful
		
		System.out.println("result is "+result);
		if(result!=true) 
			fail(); //result should be true
	}
	
	@Test
	public void test2() {
		BankAccount account=new BankAccount("Vivek", "p@ss", 20000);
		
		boolean result=account.withdraw(5000, "p@ss2"); //should be successful
		
		System.out.println("result is "+result);
		if(result==true)
			fail("widthrawal succeeded with wrong data"); //result should be false
	}
	
	

}
