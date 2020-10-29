package in.conceptarchitect.banking.tests;

import static in.conceptarchitect.utils.CustomAsserts.assertLessThan;
import static in.conceptarchitect.utils.CustomAsserts.assertType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.OverDraftAccount;

public class OverdraftAccountTests {

	String password="pass";
	double balance=10000;
	OverDraftAccount account;
	
	@Before
	public void init() {
		account=new OverDraftAccount("some name",password, balance);
		
	}
	
	@Test
	public void account_isATypeOfSavingAccount() {
		assertType(BankAccount.class, account);
		
	}
	
	private void assertOdLimit(double expectedOdLimit) {
		assertEquals(expectedOdLimit, account.getOdLimit(),0.01);
	}
	
	
	@Test
	public void account_hasOdLimitAs10PercentOfInitialDeposit() {
		
		double expectedOdLimit= balance*0.1;
		
		
		//assertEquals(expectedOdLimit,account.getOdLimit(),0.01);
		
		assertOdLimit(expectedOdLimit);
		
		
		
	}
	
	//@Ignore 
	@Test
	public void odLimit_increasesOdLimitIfBalanceIncreasesToHistoricHightestValue() {
		double extra=10000;
		account.deposit(extra); //now balance becomes balance+extra
		
		double newOdLimit=(extra+balance)*0.1;
		
		assertOdLimit(newOdLimit);
	}
	
	//@Ignore
	@Test
	public void odLimit_doesntChangeOnWithdrawal() {
		
		double odLimitBeforeWithdrawal=account.getOdLimit();
		
		account.withdraw(5000, password);
		
		
		assertOdLimit(odLimitBeforeWithdrawal);
	}
	
	
	@Test
	public void odLimit_canIncreaseIfCreditInterestIncreasesAccountBalanceUptoHistoricMax() {
		
		account.creditInterest(12);
		double newBalance= account.getBalance();
		double expectedOdLimit=newBalance*0.1;
		
		assertOdLimit(expectedOdLimit);
		
	}
	
	//@Ignore
	@Test
	public void odLimit_doesntChangeIfNewBalanceIsLessThanHistoricMaxBalance() {
		//arrange
		double historicOdLimit=account.getOdLimit(); //this was odLimt at hightest point
				
		account.withdraw(2000, password); //lets withdraw some money
		
		
		//now add money less that withdrawal
		account.deposit(1000); 
		
		//my currentBalance < historicBalance
		
		//but my odLimit should be based on highestHistoricBalance
		assertOdLimit(historicOdLimit);
		
	}
	

	//@Ignore
	@Test
	public void withdraw_canWithdrawUptoBalancePlusOdLimit() {
		double odLimit=account.getOdLimit();
		double maxAmount=balance+odLimit;
		
		boolean result=account.withdraw(maxAmount, password);
		
		assertTrue(result);
		
	}

	//@Ignore
	@Test
	public void withdraw_canPushMyBalanceToNegative() {
		
		double odLimit=account.getOdLimit();
		
		account.withdraw(balance+odLimit/2, password);
		
		
		assertLessThan(0, account.getBalance());
		
	}
	
	//@Ignore
	@Test
	public void withdraw_overlimitAttracts1PCOdFee() {
		
		//Arrange
		double odLimit=account.getOdLimit(); //1000
		 
		double odAmount= odLimit/2;  //500
		
		double expectedOdFee= odAmount*0.1; //1% of odverdraft  50
		
		double toWithdraw= balance+odAmount;  // 10000+500
		
		double expectedBalance= - odAmount - expectedOdFee; //-500-50 = -550
		
		
		//Act
		boolean result=account.withdraw(toWithdraw, password);
		
		assertTrue(result);
		
		
		assertEquals(expectedBalance, account.getBalance(),0.01);
		
	
	}
	
	
	
	
	
	
}
