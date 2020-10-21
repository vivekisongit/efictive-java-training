package com.bank.module;

public class BankAccount {
	
	int accountNumber;
	String name;
	String password;
	double balance;
	double interestRate;
	
	/*No need to set account no again*/
	public int getAccountNumber() {
		return accountNumber;
	}
	
	/*Name should be change*/	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	/*No need to set balance*/
	public double getBalance() {
		return balance;
	}
	
	/*Interest should be change*/
	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	
	
	
	public  BankAccount(int accountNumber, String name, String password, double amount, double rate) {
		createAccount(accountNumber, name, password,amount, rate);
	}
	
	
	/*No one call's this method from outside*/
	private void createAccount(int accountNumber, String name, String password, double amount, double rate) {

		this.accountNumber=accountNumber;
		this.name=name;  
		this.password=password;		
		this.balance=amount; 
		interestRate=rate;   
	}
	/*Method to show the BankAccount details*/
	public void show() {
		System.out.println("Account Number\t"+this.accountNumber); 
		System.out.println("Name\t"+name); 
		System.out.println("Password\t"+password); 
		System.out.println("Balance\t"+balance);
		System.out.println("Interest Rate\t"+interestRate); 
	}
	
	public void deposit(int amount) {		
		if(amount>0) {
			balance+=amount;
			System.out.println("Amount deposited successfully");
		}
		else {
			System.out.println("Invalid Amount deposit Failed");
		}
		
	}

	public void withdraw(int amount, String password) {		
		if(!this.password.equals(password)) {
			System.out.println("invalid credentials");
			return ;
		}else if(amount<=0) {
			
			System.out.println("invalid denomination. please enter positive amount");
			
		} else if(amount> balance) {			
			System.out.println("Insufficient Funds");
			
		}else {			
			balance-=amount;
			System.out.println("please collect your cash");
			
		}
		
	}

	public void creditInterest() {		
		balance+=(balance*interestRate)/1200;
	}

}
