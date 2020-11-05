package in.conceptarchitect.banking.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;



public class FluentTests {

	
	@Test
	public void creditInterest_canIncreaseTheBalance() {
		
		double actualBalance=10000;
		double rate=12;
		
		double expectedBalance=actualBalance+actualBalance*rate/1200;
		
		
		double newBalance= new SavingsAccount("Vivek", "pass", actualBalance)
								.creditInterest(rate)
								.getBalance();
		
		assertEquals(expectedBalance,newBalance,0.01);
		
		
	}
	
	@Test
	public void withdraw_canImmediatelyUseNewPasswordAfterChanging() {
		String password="password";
		String newPassword="new password";
		double balance=50000;
		double amount=5000;
		double rate=12;
		
		double balanceAterDeposit= balance+amount;
		double balanceAfterCreditInterest=balanceAterDeposit*(1+rate/1200);
		double balanceAfterWithdraw=balanceAfterCreditInterest-amount;
		
		
		SavingsAccount account=new SavingsAccount("Vivek", password, 50000);
		
		
		double actualBalance=account
							.deposit(amount)
							.creditInterest(rate)
							.changePassword(password, newPassword)
							.withdraw(amount, newPassword)
							.getBalance();
		
		
		assertEquals(balanceAfterWithdraw,actualBalance,0.01);
		
		
		
		
		
		
	}

}
