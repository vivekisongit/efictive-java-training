package com.bank.bankaccount;

import com.bank.bankinterface.BankSystem;

public class Bank implements BankSystem{
	static int accountCount = 0;
	double interestRate = 10;

	SavingBankAccount bankAccountArr[] = new SavingBankAccount[10];

	/* Interest should be change */
	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public void openAccount(String name, String password, double amount) {
		SavingBankAccount bankAccount = new SavingBankAccount(name, password, amount, this.interestRate);
		bankAccount.accountNumber=++accountCount;
		for (int i = 0; i < 10; i++) {
			if (bankAccountArr[i] == null) {
				bankAccountArr[i] = bankAccount;
				break;
			}
		}
		System.out.println("Account opened successfully");
		System.out.println("Your account no is:" + bankAccount.accountNumber);

	}

	public String withdraw(int accNo, String pass, double amt) {
		String result = "";
		for (int i = 0; i < 10; i++) {
			SavingBankAccount bankAccount = (SavingBankAccount) bankAccountArr[i];
			if (bankAccount.getAccountNumber() == accNo) {
				result = bankAccount.withdraw((int) amt, pass);
				System.out.println("Withdraw successfully");
				break;
			}
		}
		return result;
	}

	public String deposit(int accNo, double amt) {
		String result = "";
		for (int i = 0; i < 10; i++) {
			SavingBankAccount bankAccount = (SavingBankAccount) bankAccountArr[i];
			if (bankAccount.getAccountNumber() == accNo) {
				result = bankAccount.deposit((int) amt);
				System.out.println("deposited successfully");
				break;
			}
		}
		return result;
	}

	public boolean changePassword(int accNo, String oldPass, String newPass) {
		boolean result = false;
		for (int i = 0; i < 10; i++) {
			SavingBankAccount bankAccount = (SavingBankAccount) bankAccountArr[i];
			if (bankAccount.getAccountNumber() == accNo) {
				result = bankAccount.changePassword(oldPass, newPass);
				if (result) {
					System.out.println("Password changes successfully");
					break;
				}
			}
		}
		return result;
	}

	public boolean creditInterest(int accNo) {
		boolean result=false;
		for (int i = 0; i < 10; i++) {
			SavingBankAccount bankAccount = (SavingBankAccount) bankAccountArr[i];
			if (bankAccount.getAccountNumber() == accNo) {
				bankAccount.creditInterest();
				System.out.println("Credit interested successfully" + bankAccount.balance);
				result= true;
				break;
			}
		}
		return result;

	}

	public String showAccountDetails(int accNo) {
		String result = "Error";
		for (int i = 0; i < 10; i++) {
			SavingBankAccount bankAccount = (SavingBankAccount) bankAccountArr[i];
			if (bankAccount.getAccountNumber() == accNo) {
				result = bankAccount.showAccountDetails();
				System.out.println(result);
				break;
			}
		}		
		return result;
	}

	public boolean closeAccount(int accountNo) {
		boolean result=false;
		for (int i = 0; i < 10; i++) {
			SavingBankAccount bankAccount = (SavingBankAccount) bankAccountArr[i];
			if (bankAccount.getAccountNumber() == accountNo) {
				bankAccountArr[i] = null;
				System.out.println("Account closed successfully");
				result= true;

			}
		}
		return result;

	}
}
