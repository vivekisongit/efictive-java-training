package in.conceptarchitect.banking;

import in.conceptarchitect.utils.Input;

public class BankAccount {
	
	
	int accountNumber;
	String name;
	String password;
	double balance;
	
	
	
	public  BankAccount( String name, String password, double amount) {
		
		//this.accountNumber= ++accountCount; //will be supplied by the bank
		this.name=name;  
		this.password=salt(password); 		
		
		this.balance=amount; 
		   
	}
	
	

	//Its a dummy and non-secured logic for password hashing just to demonstrated
	//the idea. Search for password hashing algorithm for better logic
	private String salt(String password) {
		String s="";
		for(int i=0;i<password.length();i++) {
			char ch=password.charAt(i);
			int v=(int)ch;
			
			s+= Integer.toHexString(v);
		}
		return s;
	}
	
	public boolean authenticate(String password) {
		return salt(password).equals(this.password);
	}
	
	public boolean changePassword(String oldPassword, String newPassword) {
		
		if(authenticate(oldPassword)) {
			password=salt(newPassword);
			return true;
		}else
			return false;
		
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	//balance
	public double getBalance() {
		return balance;
	}
	
	
	
	
	public void show() {
		System.out.println("Account Number\t"+this.accountNumber); 
		System.out.println("Name\t"+name); 
		//System.out.println("Password\t"+password); 
		System.out.println("Balance\t"+balance); 
	 
	}
	
	public boolean deposit(double amount) {
		
		if(amount>0) {
			balance+=amount;
			return true;	//System.out.println("Amount deposited successfully");
		}
		else {
			return false;//System.out.println("Invalid Amount. Deposit Failed");
		}
		
	}

	public boolean withdraw(double amount, String password) {
		// TODO Auto-generated method stub
		
		if(!authenticate(password)) {			
			return true;
		}else if(amount<=0) {
			
			return false;
			
		} else if(amount> balance) {
			
			return false;
			
		}else {
			
			
			return true;
			
		}
		
	}

	public void creditInterest(double interestRate) {
		// TODO Auto-generated method stub
		balance+=(balance*interestRate)/1200; //one month interest at a time.
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%d\t%f\t%s", accountNumber,balance,name);
	}
	
	
	
}
