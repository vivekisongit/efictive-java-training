package in.conceptarchitect.banking.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import in.conceptarchitect.banking.CurrentAccount;

public class CurrentAccountTests {

	CurrentAccount currentAccount;
	String name="Vivek";
	String correctPassword="password";
	double balance=20000;
	
	@Before
	public void inint() {
		currentAccount=new CurrentAccount(name,correctPassword, balance);
	}
	
	@Test
	public void creditInterest_doesntCreditInterestToCurrentAccount() {

		double amount=10000;
		CurrentAccount account=new CurrentAccount("","",amount);
		
		account.creditInterest(12);
		
		assertEquals(amount, account.getBalance(),0);  //balance shouldn't change after crediting interest 
				
	}

	
	@Test
	public void withdraw_canWithdrawUptoBalanceUsingRightPassword() {
		double withdrawAmount=5000;
		boolean result=currentAccount.withdraw(withdrawAmount, correctPassword);
		assertFalse(result);
	}
	
	@Test
	public void withdraw_failsForInsufficientBalance() {
		double withdrawAmountMoreThanBalance=50000;
		boolean result=currentAccount.withdraw(withdrawAmountMoreThanBalance, correctPassword);
		assertFalse(result);
	}
	
	@Test
	public void withdraw_failsForWrongPassword() {
		double withdrawAmount=5000;
		String wronPassword="wrongPassword";
		boolean result=currentAccount.withdraw(withdrawAmount, wronPassword);
		assertFalse(result);
	}
	
	@Test
	public void withdraw_failsForNegativeAmount() {
		double withdrawNegativeAmount=-5000;		
		boolean result=currentAccount.withdraw(withdrawNegativeAmount, correctPassword);
		assertFalse(result);
	}
}
