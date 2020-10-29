package in.conceptarchitect.banking.tests;

import static in.conceptarchitect.utils.CustomAsserts.assertType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import in.conceptarchitect.banking.Bank;
import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.SavingsAccount;
import in.conceptarchitect.banking.exceptions.InsufficientBalanceException;
import in.conceptarchitect.banking.exceptions.InvalidAccountNumberException;
import in.conceptarchitect.banking.exceptions.InvalidCredentialsException;

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
	
	@Before  
	public void init() {		
		firstAccountNo=bank.openAccount("savings",name1,correctPassword1, balance1);
		secondAccountNo=bank.openAccount("savings",name2,correctPassword2, balance2);
	}
	
	@Test(expected = InvalidAccountNumberException.class)
	public void transfer_shouldFailIfFromAccountDoesntExist() {
		int wrongFromAccount=9;
		double transferAmount=1000;
		bank.transfer(wrongFromAccount, transferAmount, correctPassword1, secondAccountNo); 		
		
	}
	
	@Test(expected = InvalidAccountNumberException.class)
	public void transfer_shouldFailIfInvalidToAccount() {
		int wrongToAccount=9;
		double transferAmount=1000;
		bank.transfer(firstAccountNo, transferAmount, correctPassword1, wrongToAccount); 	
		
	}
	
	@Test(expected = InvalidCredentialsException.class)
	public void transfer_shouldFailForInvalidPassword() {		
		double transferAmount=1000;
		String wrongPassword="wrong-password";
		bank.transfer(firstAccountNo, transferAmount, wrongPassword, secondAccountNo); 	
		
		
	}
	
	@Test(expected = InsufficientBalanceException.class)
	public void transfer_shouldFailForInsufficientBalance() {
		double amountMoreThanAccountBalance=30000;
		bank.transfer(firstAccountNo, amountMoreThanAccountBalance, correctPassword1, secondAccountNo); 	
		
	}
	
	@Test
	public void transfer_shouldSucceedInHappyPath() {
		bank.transfer(firstAccountNo, 1000, correctPassword1, secondAccountNo); 	
	
	}
	
	@Test
	public void creditInterest_creditsOneMonthOfInterest() {
		bank.creditInterests(); 		
	}
	
	@Test(expected = InvalidAccountNumberException.class)
	public void closeAccount_failsForInvalidAccountNumber() {
		int invalidAccountNo=8;
		bank.close(invalidAccountNo, correctPassword1);//passing invalid account no
		

	}
	
	@Test(expected = InvalidAccountNumberException.class)
	public void closeAccount_cantWithdrawFromClosedAccount() {
		int closedAccountNo=9;
		double withdrawAmount=1000;
		bank.withdraw(closedAccountNo, withdrawAmount, correctPassword1);
		
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
		int oneAccount=bank.openAccount("savings","Sachin","p@ss", balance1);
		int nextAccount=bank.openAccount("savings","Ravi","P@ss", balance2);
		
		assertEquals(oneAccount, nextAccount,0); //next account must be greater than one
		
	}
	

}
