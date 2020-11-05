package in.conceptarchitect.banking.repository;

import java.util.ArrayList;
import java.util.Collection;

import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.exceptions.InvalidAccountNumberException;

public class ArrayListAccountRepository implements AccountRepository{
	
	ArrayList<BankAccount> accounts=new ArrayList<BankAccount>();
	private int accountCount=0;
	 
	public int addAccount(BankAccount account) {
	
		accounts.add(account);
		accountCount++;
		//return the account Number
		return account.getAccountNumber();
	}
	
	public BankAccount getAccountById(int accountNumber) {
		if(accountNumber<1 || accountNumber>accountCount )		
			throw new InvalidAccountNumberException(accountNumber);
		
		for(BankAccount account: accounts)
			if(account.getAccountNumber()==accountNumber)
				return account;
		
		throw new InvalidAccountNumberException(accountNumber);
	}
	
	public void removeAccount(int accountNumber) {
		BankAccount account=getAccountById(accountNumber);
		accounts.remove(account);
	}
	
	public Collection<BankAccount> getAllAccounts() {
		return accounts;
	}
	
	 

}
