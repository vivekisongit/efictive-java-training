package in.conceptarchitect.banking.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import in.conceptarchitect.banking.OverDraftAccount;

public class OverdraftAccountTests {


	OverDraftAccount account;
	String name="Vivek";
	String password="p@ss";
	int balance=20000;
	int balanceWithOdLimit=balance+(balance%10);
	int historicMax=25000;
	@Before
	public void init() {
		
		account=new OverDraftAccount(name, password, balance);
		
	}
	@Test
	public void account_hasOdLimitAs10PercentOfInitialDeposit() {
		double odLimit=balance%10;	
		assertEquals(odLimit, account.getOdLimit(),0.1);  
	}
	
	@Test
	public void odLimit_increasesOdLimitIfBalanceIncreasesToHistoricHightestValue() {
	
		assertEquals(historicMax, account.getBalance()+5000,0.1); 

	}
	
	@Test
	public void odLimit_doesntChangeOnWithdrawal() {
		int withdrawAmount=5000;
		boolean result=account.withdraw(withdrawAmount, password);
		assertTrue(result);
	}
	
	@Test
	public void odLimit_canIncreaseIfCreditInterestIncreasesAccountBalanceUptoHistoricMax() {
		fail("Implement the logic here");
	}
	
	@Test
	public void odLimit_doesntChangeIfNewBalanceIsLessThanHistoricMaxBalance() {
		fail("Implement the logic here");
	}
	
		
	@Test
	public void withdraw_canWithdrawUptoBalancePlusOdLimit() {
		boolean result=account.withdraw(balanceWithOdLimit, password);
		assertFalse(result);
	}

	@Test
	public void withdraw_canPushMyBalanceToNegative() {
		account.withdraw(balanceWithOdLimit, password);
		if(account.getBalance()<0) {
			assertFalse(true);
		}
	}
	
	@Test
	public void withdraw_overlimitAttracts1PCOdFee() {
		fail("Implement the logic here");
	}
	
	
	
	
	
	
}
