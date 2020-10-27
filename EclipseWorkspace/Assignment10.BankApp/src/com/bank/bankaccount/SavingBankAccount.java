package com.bank.bankaccount;

public class SavingBankAccount extends Bank {

	int accountNumber;
	String name;
	String password;
	double balance;

	public boolean authenticate(String password) {
		return password.equals(this.password);
	}

	/* No need to set account no again */
	public int getAccountNumber() {
		return accountNumber;
	}

	/* Name should be change */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* No need to set balance */
	public double getBalance() {
		return balance;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/* No one call's this method from outside */
	protected SavingBankAccount(String name, String password, double amount, double rate) {		
		this.name = name;
		this.password = password;
		this.balance = amount;
		interestRate = rate;
	}

	
	public String showAccountDetails() {
		return "BankAccount [accountNumber=" + accountNumber + ", name=" + name + ", balance=" + balance + "]";
	}
	

	public String deposit(int amount) {
		if (amount > 0) {
			balance += amount;
			return "Amount deposited successfully";			
		} else {
			return "Invalid Amount deposit Failed";		
		}

	}

	public String withdraw(int amount, String password) {
		if (!this.password.equals(password)) {
			return "invalid credentials";
		} else if (amount <= 0) {
			return "invalid denomination. please enter positive amount";
		} else if (amount > balance) {
			return "Insufficient Funds";
		} else {
			balance -= amount;
			return "please collect your cash";
		}

	}

	public void creditInterest() {
		balance += (balance * interestRate) / 1200;
	}

	public boolean changePassword(String oldPassword, String newPassword) {

		if (authenticate(oldPassword)) {
			password = newPassword;
			return true;
		} else
			return false;

	}

}
