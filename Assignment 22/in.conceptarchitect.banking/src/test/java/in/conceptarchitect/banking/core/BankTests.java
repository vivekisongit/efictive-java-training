package in.conceptarchitect.banking.core;

import static in.conceptarchitect.testing.CustomAsserts.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import in.conceptarchitect.banking.exceptions.InsufficientBalanceException;
import in.conceptarchitect.banking.exceptions.InvalidAccountNumberException;
import in.conceptarchitect.banking.exceptions.InvalidCredentialsException;
import in.conceptarchitect.banking.exceptions.InvalidDenominationException;
import in.conceptarchitect.banking.repository.AccountRepository;
import in.conceptarchitect.banking.repository.HashmapAccountRepository;

public class BankTests {
	
	Bank bank;
	double rate=12;
	double amount=50000;
	String password="pass";
	BankAccount one,two,three;
	AccountRepository repository;
	
	@Before
	public void init() {
		repository=new HashmapAccountRepository();
		
		bank=new Bank("ICICI",rate,repository);
		
		bank.openAccount("SavingsAccount", "S", password, amount);
		bank.openAccount("CurrentAccount", "C", password, amount);
		bank.openAccount("OverDraftAccount", "O", password, amount);
		
		one=bank.getAccount(1, password);
		two=bank.getAccount(2, password);
		three=bank.getAccount(3, password);
	}
	
	
	
	@Test
	public void openAccount_withSavingsTypeCreatesASavingAccount() {
		assertType(SavingsAccount.class, one);
	
	}
	
	@Test
	public void openAccount_withCurrentTypeCreatesACurrentAccount() {
		assertType(CurrentAccount.class, two);
	
	}
	
	@Test
	public void openAccount_withOverdraftTypeCreatesAOverdraftAccount() {
		assertType(OverDraftAccount.class, three);
	
	}
	
	
	 @Test
	public void bank_hasInitiallyThreeAccounts() {
		
		assertEquals(3, bank.getAccountCount());
	}
	
	
	@Test(expected=InvalidAccountNumberException.class)
	public void transfer_shouldFailWithInvalidAccountExceptionIfFromAccountDoesntExist() {
		bank.transfer(100, 1, password, 1);
	}
	
	@Test(expected=InvalidAccountNumberException.class)
	public void transfer_shouldFailIfInvalidToAccount() {
		bank.transfer(1, 1, password, 100);
	}
	
	@Test(expected=InvalidCredentialsException.class)
	public void transfer_shouldFailWithInvalidCredentialsExceptionForInvalidPassword() {
		bank.transfer(1, 1, "wrong-password", 2);
	}
	
	@Test(expected=InsufficientBalanceException.class)
	public void transfer_shouldFailWithInsufficientBalanceExceptionForInsufficientBalance() {
		bank.transfer(1, amount+1, password, 2);
		
	}
	
	@Test
	public void transfer_shouldSucceedInHappyPath() {
		bank.transfer(1, 1, password, 2);
		
		assertEquals(amount-1, one.getBalance(),0.0);
		assertEquals(amount+1, two.getBalance(),0.0);
		
	}
	
	@Test
	public void creditInterest_creditsOneMonthOfInterestToSavingsAndOverdraftAccount() {
		
		double amountAfterInterest= amount+(amount*rate/1200);
		
		bank.creditInterests();
		
		assertEquals(amountAfterInterest,one.getBalance(),0.01);
		assertEquals(amountAfterInterest,three.getBalance(),0.01);
		
		
	}
	
	@Test(expected = InvalidAccountNumberException.class)
	public void closeAccount_failsForInvalidAccountNumber() {
		bank.closeAccount(500, password);
	}
	
	@Test(expected=InvalidAccountNumberException.class)
	public void closeAccount_cantWithdrawFromClosedAccount() {
		//ARRANGE --> close an account
		bank.closeAccount(2, password);
		//ACT --> try to withdraw and see it crash
		bank.withdraw(2, 1, password);
		
		
	}
	
	@Test(expected=InsufficientBalanceException.class)
	public void closeAccount_failsWithInsufficientBalanceExceptionIfBalanceIsNegative() {
		//ARRANGE --> witdraw money from the BankAccountClass
		int accountNumber=3;  //see assumption failing for account 1 or 2
		
		try {
			bank.withdraw(accountNumber, amount+1, password);
			//ASSUME
			Assume.assumeTrue(bank.getAccount(accountNumber, password).getBalance()<0);
			
		} catch(Exception ex) {
			Assume.assumeFalse("Can't Withdraw Fund", true);
		}
		
		
		//ACT ---> try to close the account
		bank.closeAccount(accountNumber, password);
		
	}
	 

	@Test(expected=InvalidDenominationException.class)
	public void deposit_failsForNegativeAmount() {
		bank.deposit(3, -1);
		
	}
	
	
	//@Ignore @Test
	public void openAccount_AssignsSequentialAccountNumber() {
		fail("Implement the logic here");
	}
	

}
