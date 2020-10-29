package in.conceptarchitect.banking.repository;

import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.exceptions.InvalidAccountNumberException;

public class ArrayAccountRepository {
	
	public final int MAX_ACCOUNTS=10; //PROBLEM: we can't have more than this much account

	BankAccount [] accounts=new BankAccount[MAX_ACCOUNTS];

	private int accountCount=0;
	
	public int addAccount(BankAccount account) {
		//add the account to account list		
		//account number x will be stored on location x
		//we will never use index 0 to store a account
		getAllAccounts()[account.getAccountNumber()]=account;  //add all accounts to the same collection
		accountCount++;
		//return the account Number
		return account.getAccountNumber();
	}
	
	public BankAccount getAccountById(int accountNumber) {
		if(accountNumber<1 || accountNumber>accountCount || getAllAccounts()[accountNumber]==null)
			//return null; //no such account
			throw new InvalidAccountNumberException(accountNumber);
		
		BankAccount account=getAllAccounts()[accountNumber];
		return account;
	}
	
	public void removeAccount(int accountNumber) {
		getAllAccounts()[accountNumber]=null; //remove the account
	}
	
	public BankAccount[] getAllAccounts() {
		return accounts;
	}
	
	

}
