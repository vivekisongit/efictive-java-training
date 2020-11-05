package in.conceptarchitect.banking.core;

import in.conceptarchitect.banking.exceptions.InsufficientBalanceException;
import in.conceptarchitect.banking.repository.AccountRepository;

public class Bank {
	
	int accountCount=0;   	
	String name;
	double interestRate;
	
	
	//storage for BankAccounts
	AccountRepository accounts;
	
	
	
	public Bank(String name, double interestRate, AccountRepository accounts) {
	
		this.interestRate=interestRate;
		this.name=name;
		this.accounts=accounts;
		
		 
	}
	
	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public int getAccountCount() {
		return accountCount;
	}

	public String getName() {
		return name;
	}
	
	
	
	public void deposit(int accountNumber, double amount) {
		BankAccount account = accounts.getAccountById(accountNumber);
		account.deposit(amount);
		accounts.save(account);
	}
	
	public void changePassword(int accountNumber, String currentPassword, String newPassword) {
		BankAccount account=accounts.getAccountById(accountNumber);
		account.changePassword(currentPassword, newPassword);
		accounts.save(account);
	}
	
	
	public int openAccount(BankAccount account,String name, String password,  double amount) {	
			
		//Bank should set the account Number which is accessible due to package scope
		account.accountNumber=++accountCount;
		
		return accounts.addAccount(account);
	}

	public void closeAccount(int accountNumber, String password) {
		BankAccount account = accounts.getAccountById(accountNumber);
		
		account.authenticate(password);
				
		
		if(account.getBalance()<0)
			throw new InsufficientBalanceException(accountNumber, -account.getBalance()," You need to clear the overdue to close your account");
		
		
		accounts.removeAccount(accountNumber);

	}


	public void withdraw(int accountNumber, double amount, String password) {
		BankAccount account = accounts.getAccountById(accountNumber);
		account.withdraw(amount, password); //may return success or falure
		
		accounts.save(account);
	}
	
	
	public void transfer(int sourceAccountNumber,  double amount, String password,int targetAccountNumber) {
		
		BankAccount target=accounts.getAccountById(targetAccountNumber);
		BankAccount src = accounts.getAccountById(sourceAccountNumber);
		
		src.withdraw(amount, password);
		target.deposit(amount);
		
		accounts.save(src);
		accounts.save(target);
		
	}

	public void printAccountList() {

		System.out.println("Account\tBalance\tName");
		
		for(BankAccount a: accounts.getAllAccounts()) {
			
			if(a!=null) //account may have been closed
				System.out.println(a); //use toString() method
		}
	}

	
	public void creditInterests() {

		
		for(BankAccount a: accounts.getAllAccounts()) {
			
			if(a!=null) {
				a.creditInterest(interestRate);
				accounts.save(a);
			}
		}
		
		
	}

	
	public String getAccountInfo(int accountNumber, String pin) {
		// TODO Auto-generated method stub
		BankAccount account= accounts.getAccountById(accountNumber);
		account.authenticate(pin);
		return account.toString();
		
	}

	public BankAccount getAccount(int accountNumber, String password) {
		// TODO Auto-generated method stub
		BankAccount account=accounts.getAccountById(accountNumber);
		account.authenticate(password);
		return account;
	}
	
	
}
