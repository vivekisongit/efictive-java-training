package in.conceptarchitect.banking.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import in.conceptarchitect.banking.CurrentAccount;
import in.conceptarchitect.banking.exceptions.InsufficientBalanceException;
import in.conceptarchitect.banking.exceptions.InvalidCredentialsException;
import in.conceptarchitect.banking.exceptions.InvalidDenominationException;

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
		currentAccount.withdraw(withdrawAmount, correctPassword);
		
	}
	
	@Test(expected = InsufficientBalanceException.class)
	public void withdraw_failsForInsufficientBalance() {
		double withdrawAmountMoreThanBalance=50000;
		currentAccount.withdraw(withdrawAmountMoreThanBalance, correctPassword);
	}
	
	@Test(expected = InvalidCredentialsException.class)
	public void withdraw_failsForWrongPassword() {
		double withdrawAmount=5000;
		String wronPassword="wrongPassword";
		currentAccount.withdraw(withdrawAmount, wronPassword);
		
	}
	
	@Test(expected = InvalidDenominationException.class)
	public void withdraw_failsForNegativeAmount() {
		double withdrawNegativeAmount=-5000;		
		currentAccount.withdraw(withdrawNegativeAmount, correctPassword);
		
	}
}
