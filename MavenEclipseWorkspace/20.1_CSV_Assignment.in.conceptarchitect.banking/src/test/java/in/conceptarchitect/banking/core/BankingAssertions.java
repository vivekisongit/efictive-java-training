package in.conceptarchitect.banking.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;



public class BankingAssertions {

	BankAccount account;

	public BankingAssertions(BankAccount account) {
		this.account = account;
	}
	
	public void assertBalance(double expectedBalance) {		
		assertEquals(expectedBalance, account.getBalance(),0.01);
	}
	
	public void assertBalanceLessThan(double expectedMaxBalance) {
		if(expectedMaxBalance<=account.getBalance())
			fail("expectedBalance:"+expectedMaxBalance+" is not less than actual balance"+account.getBalance());
	}
	

}
