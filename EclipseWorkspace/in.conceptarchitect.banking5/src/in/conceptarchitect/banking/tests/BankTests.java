package in.conceptarchitect.banking.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import in.conceptarchitect.banking.Bank;
import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.SavingsAccount;
import static in.conceptarchitect.utils.CustomAsserts.*;

public class BankTests {
	
	@Test
	public void transfer_shouldFailIfFromAccountDoesntExist() {
		fail("Implement the logic here");
	}
	
	@Test
	public void transfer_shouldFailIfInvalidToAccount() {
		fail("Implement the logic here");
	}
	
	@Test
	public void transfer_shouldFailForInvalidPassword() {
		fail("Implement the logic here");
	}
	
	@Test
	public void transfer_shouldFailForInsufficientBalance() {
		fail("Implement the logic here");
	}
	
	@Test
	public void transfer_shouldSucceedInHappyPath() {
		fail("Implement the logic here");
	}
	
	@Test
	public void creditInterest_creditsOneMonthOfInterest() {
		fail("Implement the logic here");
	}
	
	@Test
	public void closeAccount_failsForInvalidAccountNumber() {
		fail("Implement the logic here");
	}
	
	@Test
	public void closeAccount_cantWithdrawFromClosedAccount() {
		fail("Implement the logic here");
	}
	
	@Test
	public void openAccount_withSavingsTypeCreatesASavingAccount() {
		
		Bank bank=new Bank("bank name",1);
		
		bank.openAccount("savings", "someone", "somepassword", 1000);
		
		BankAccount account=bank.getAccount(1,"somepassword");
		
		assertType(SavingsAccount.class, account);
		
	}
	
	
	@Test
	public void openAccount_AssignsSequentialAccountNumber() {
		fail("Implement the logic here");
	}
	

}
