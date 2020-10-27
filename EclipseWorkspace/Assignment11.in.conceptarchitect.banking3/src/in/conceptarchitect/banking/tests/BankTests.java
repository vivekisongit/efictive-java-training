package in.conceptarchitect.banking.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import in.conceptarchitect.banking.Bank;
import in.conceptarchitect.banking.BankAccount;

public class BankTests {
	
	
	Bank bank=new Bank("ICICI", 10);
	
	int firstAccountNo=0;
	int secondAccountNo=0;
	
	BankAccount account1;
	String name1="Vivek";
	String correctPassword1="password";
	double balance1=20000;
	
	BankAccount account2;
	String name2="Rahul";
	String correctPassword2="password";
	double balance2=10000;
	
	@Before   //this will be called before every test
	public void init() {		
		firstAccountNo=bank.openAccount(name1,correctPassword1, balance1);
		secondAccountNo=bank.openAccount(name2,correctPassword2, balance2);
	}
	@Test
	public void transfer_shouldFailIfFromAccountDoesntExist() {
		boolean result=bank.transfer(firstAccountNo, 500, correctPassword1, 5); 		
		if(result) {
			fail("Target account no is invalid");
		}
	}
	
	@Test
	public void transfer_shouldFailIfInvalidToAccount() {
		boolean result=bank.transfer(firstAccountNo, 500, correctPassword1, 6); 	
		if(result) {
			fail("Target account no is invalid");
		}
	}
	
	@Test
	public void transfer_shouldFailForInvalidPassword() {
		boolean result=bank.transfer(firstAccountNo, 500, "wrong-password", secondAccountNo); 	
		if(result) {
			fail("Password for account is wrong");
		}
	}
	
	@Test
	public void transfer_shouldFailForInsufficientBalance() {
		boolean result=bank.transfer(firstAccountNo, 30000, "wrong-password", secondAccountNo); 	
		if(result) {
			fail("Insufficent funds");
		}
	}
	
	@Test
	public void transfer_shouldSucceedInHappyPath() {
		boolean result=bank.transfer(firstAccountNo, 1000, correctPassword1, secondAccountNo); 	
		if(result) {
			fail("Happy path");
		}
	}
	
	@Test
	public void creditInterest_creditsOneMonthOfInterest() {
		bank.creditInterests(); 	
		
	}
	
	@Test
	public void closeAccount_failsForInvalidAccountNumber() {
			boolean result=bank.close(8, correctPassword1);//passing invalid account no
			if(result) {
				fail("Passing invalid account no");
			}
	}
	
	@Test
	public void closeAccount_cantWithdrawFromClosedAccount() {
		boolean result=bank.withdraw(4, 1000, correctPassword1);//passing closed account no
		if(result) {
			fail("cant withdraw");
		}
	}
	
	
	@Test
	public void openAccount_AssignsSequentialAccountNumber() {
	System.out.println("Not yet implemented");
	}
	

}
