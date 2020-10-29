package in.conceptarchitect.banking.repository;

import java.util.ArrayList;

import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.exceptions.InvalidAccountNumberException;

public class ArrayListAccountRepository {
	
	

	ArrayList<BankAccount> listaccounts=new ArrayList<BankAccount>();
	

	private int accountCount=0;
	
	public int addAccount(BankAccount account) {
		//add the account to account list		
		//account number x will be stored on location x
		//we will never use index 0 to store a account
		getAllAccounts().add(account.getAccountNumber(), account); //add all accounts to the same collection
		 
		accountCount++;
		//return the account Number
		return account.getAccountNumber();
	}
	
	public BankAccount getAccountById(int accountNumber) {
		if(accountNumber<1 || accountNumber>accountCount || getAllAccounts().get(accountNumber)==null)
			//return null; //no such account
			throw new InvalidAccountNumberException(accountNumber);
		
		BankAccount account=getAllAccounts().get(accountNumber);
		return account;
	}
	
	public void removeAccount(int accountNumber) {
		getAllAccounts().add(accountNumber, null);
		
	}
	
	public ArrayList<BankAccount> getAllAccounts() {
		return listaccounts;
	}
	
	

}
