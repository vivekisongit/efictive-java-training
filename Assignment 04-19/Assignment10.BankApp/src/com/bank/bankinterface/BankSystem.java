package com.bank.bankinterface;

public interface BankSystem {
	public void openAccount(String name, String password, double amount);	
	public String deposit(int accNo, double amt);
	public String withdraw(int accNo, String pass, double amt);
	public String showAccountDetails(int accNo);
	
}
