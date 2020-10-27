package in.conceptarchitect.banking;

public class Bank {
	
	int accountCount=0;   	
	String name;
	double interestRate;
	public final int MAX_ACCOUNTS=10; //PROBLEM: we can't have more than this much account
	
	//storage for BankAccounts
	BankAccount [] accounts=new BankAccount[MAX_ACCOUNTS];
	
	public Bank(String name, double interstRate) {
	
		this.interestRate=10;
		this.name=name;
		
		
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
	
	
	private BankAccount getAccountById(int accountNumber) {
		if(accountNumber<1 || accountNumber>accountCount)
			return null; //no such account
		
		BankAccount account=accounts[accountNumber];
		return account;
	}
	
	public int openAccount(String name, String password, double amount) {
		
		BankAccount account=new BankAccount(name,password,amount);
		
		//Bank should set the account Number which is accessible due to package scope
		account.accountNumber=++accountCount;
		
		//add the account to account list
		
		//account number x will be stored on location x
		//we will never use index 0 to store a account
		accounts[account.accountNumber]=account;
		
		//return the account Number
		return account.accountNumber;
	}
	
	
	
	public boolean close(int accountNumber, String password) {
		BankAccount account = getAccountById(accountNumber);
		
		//TODO: validate password is correct
		if(!account.authenticate(password))
			return false; //error
		
		
		//TODO: validate it has balance>0
		if(account.getBalance()<0)
			return false; //error. can't close account with negative balance
		
		
		//TODO: close the account and return true
		accounts[accountNumber]=null; //remove the account
		return true;
	}
	
	public boolean deposit(int accountNumber, double amount) {
		BankAccount account = getAccountById(accountNumber);
		
		if(account==null)
			return false; //indicates an error
		
		return account.deposit(amount);
	}
	
	
	
	public boolean withdraw(int accountNumber, double amount, String password) {
		BankAccount account = getAccountById(accountNumber);
		
		//TODO: we must verify account exists
		if(account==null)
			return false; //indicates an error
		
		return account.withdraw(amount, password); //may return success or falure
	}

	
	
	public boolean transfer(int sourceAccountNumber,  double amount, String password,int targetAccountNumber) {
		
		BankAccount src = getAccountById(sourceAccountNumber);
		BankAccount target=getAccountById(targetAccountNumber);
		
		if(src==null)
			return false;   //indicates an error
		if(target==null) 
			return false;  //indicates an error
		 
		
		if(src.withdraw(amount, password))
			return target.deposit(amount);
		else	
			return false; //indicates an error
	}

	public void printAccountList() {

		System.out.println("Account\tBalance\tName");
		for(int i=1;i<=accountCount;i++) {
			BankAccount a=accounts[i];
			if(a!=null) //account may have been closed
				System.out.printf("%d\t%f\t%s\n",a.getAccountNumber(),a.getBalance(),a.getName());
		}
	}
	
	
	public void creditInterests() {

		System.out.println("Account\tBalance\tName");
		for(int i=1;i<=accountCount;i++) {
			BankAccount a=accounts[i];
			if(a!=null) //if not already closed
				a.creditInterest(interestRate);
		}
	}

	public String getAccountInfo(int accountNumber, String pin) {
		// TODO Auto-generated method stub
		BankAccount account= getAccountById(accountNumber);
		if(account!=null && account.authenticate(pin))
			return account.toString();
		
		
		return null; //indicates an error
	}
	
	
}
