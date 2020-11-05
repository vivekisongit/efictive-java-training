package in.conceptarchitect.banking.repository;

import java.util.Arrays;
import java.util.Collection;

import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.exceptions.InvalidAccountNumberException;

public class ArrayAccountRepository implements AccountRepository {
	
	public final int MAX_ACCOUNTS=10; //PROBLEM: we can't have more than this much account

	BankAccount [] accounts=new BankAccount[MAX_ACCOUNTS];

	private int accountCount=0;
	  
	@Override
	public int addAccount(BankAccount account) {
		//add the account to account list		
		//account number x will be stored on location x
		//we will never use index 0 to store a account
		accounts[account.getAccountNumber()]=account;  //add all accounts to the same collection
		accountCount++;
		//return the account Number
		return account.getAccountNumber();
	}
	
	@Override
	public BankAccount getAccountById(int accountNumber) {
		if(accountNumber<1 || accountNumber>accountCount || accounts[accountNumber]==null)
			//return null; //no such account
			throw new InvalidAccountNumberException(accountNumber);
		
		BankAccount account=accounts[accountNumber];
		return account;
	}
	
	@Override 
	public void removeAccount(int accountNumber) {
		accounts[accountNumber]=null; //remove the account
	}
	
	@Override
	public Collection<BankAccount> getAllAccounts() {
		return Arrays.asList(accounts);
	}
	
	

}
