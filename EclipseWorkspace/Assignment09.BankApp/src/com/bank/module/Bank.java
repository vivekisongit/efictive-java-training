package com.bank.module;

public class Bank {
	static int accountNum=1;
	double interestRate=10;
	
	BankAccount bankAccountArr[]=new BankAccount[10];
	
	/*Interest should be change*/
	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	public  void openAccount(String name, String password, double amount) {		
		BankAccount bankAccount=new BankAccount(name, password,amount,this.interestRate);
		for(int i=0; i<10; i++) {
			if(bankAccountArr[i]==null) {
				bankAccountArr[i]=bankAccount;
			}
		}
		System.out.println("Account opened successfully");
		System.out.println("Your account no is:"+bankAccount.accountNumber);
		
	}
	
	public String withdraw(int accNo, String pass, double amt) {
		String result="";
		for(int i=0; i<10; i++) {
			BankAccount bankAccount= (BankAccount)bankAccountArr[i];
			if(bankAccount.getAccountNumber()==accNo){
				result=bankAccount.withdraw((int)amt, pass);
			}
		}
		return result;
	}
	public String deposit(int accNo, double amt) {
		String result="";
		for(int i=0; i<10; i++) {
			BankAccount bankAccount= (BankAccount)bankAccountArr[i];
			if(bankAccount.getAccountNumber()==accNo){
				result=bankAccount.deposit((int)amt);
			}
		}
		return result;
	}
	public boolean changePassword(int accNo, String oldPass, String newPass) {
		boolean result=false;
		for(int i=0; i<10; i++) {
			BankAccount bankAccount= (BankAccount)bankAccountArr[i];
			if(bankAccount.getAccountNumber()==accNo){
				result=bankAccount.changePassword(oldPass, newPass);
				if(result) {
					System.out.println("Password changes successfully");
				}
			}
		}
		return result;
	}
	public boolean creditInterest(int accNo) {
		for(int i=0; i<10; i++) {
			BankAccount bankAccount= (BankAccount)bankAccountArr[i];
			if(bankAccount.getAccountNumber()==accNo){
				bankAccount.creditInterest();
				System.out.println("Credit interested successfully"+bankAccount.balance);
				return true;
				
			}
		}
		return false;
		
	}
	public String show(int accNo) {
		String result="Error";
		for(int i=0; i<10; i++) {
			BankAccount bankAccount= (BankAccount)bankAccountArr[i];
			if(bankAccount.getAccountNumber()==accNo){
				result= bankAccount.showDetails();
				
			}
		}
		System.out.println(result);
		return result;
	}
}
